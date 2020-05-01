package kz.kaspi.shop;

import kz.kaspi.person.Buyer;
import kz.kaspi.person.error.CartNotFoundException;
import kz.kaspi.shop.product.sold.SoldProduct;
import kz.kaspi.shop.product.sold.SoldProductList;
import kz.kaspi.shop.queue.Cashier;
import kz.kaspi.shop.queue.Exit;
import kz.kaspi.shop.queue.Queueable;
import kz.kaspi.shop.queue.error.NoOpenCashiersException;
import kz.kaspi.util.Logger;
import kz.kaspi.util.Serializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

/**
 * Shop class with cashiers and exit classes
 * Processes people with {@link kz.kaspi.shop.product.Product}s
 *
 * @author A.Yergali
 * @version 4.0
 */
public class Shop implements Serializable {

    private final List<Cashier> cashiers;
    private final Queueable<Buyer> exit;

    private final SoldProductList products;

    private transient ExecutorService pool;

    private static final Logger LOG = Logger.getInstance();

    private static final double X_VALUE = 344.2;
    private static final int Y_VALUE = 7;
    private static final int EXPIRE_DAYS_FOR_DISCOUNTS = 10;
    private static final double DEFAULT_DISCOUNT = 5;

    private static final int CASHIERS_TIMEOUT = 3000;
    private static final int STAND_TIMEOUT = 2000;
    private static final int EXIT_TIMEOUT = 1000;
    private static final int SHOP_TIMEOUT = 30;

    private static final String file = "shop.out";

    {
        cashiers = new ArrayList<>();
        exit = new Exit();
        products = SoldProductList.empty();

        createOpenCashier(1);
        createClosedCashier(2);
        createOpenCashier(3);
    }

    private Shop() { }

    /**
     * Add {@link Buyer} to one of the {@link Cashier}
     * or {@link Exit} if {@link Cart} is empty
     */
    public void addBuyer(Buyer buyer) {
        try {
            buyer.chooseQueue(cashiers);
        }
        catch (CartNotFoundException e) {
            exit.add(buyer);
        }
        catch (NoOpenCashiersException e) {
            LOG.error("Cannot find any open cashiers");
        }
    }

    /**
     * Creates open {@link Cashier}
     */
    public void createOpenCashier(int number) {
        cashiers.add(Cashier.open(number));
    }

    /**
     * Creates closed {@link Cashier}
     */
    public void createClosedCashier(int number) {
        cashiers.add(Cashier.closed(number));
    }

    /**
     * If shop is empty, pass new {@link Buyer}s
     * Then starts to service at cashiers
     */
    public void open(List<Buyer> buyers) {
        pool = Executors.newFixedThreadPool(cashiers.size() + 2);

        openQueues();

        if (isEmpty()) {
            pool.submit(() -> buyers.forEach(buyer -> {
                addBuyer(buyer);
                try {
                    Thread.sleep(STAND_TIMEOUT);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }

        pool.shutdown();
    }

    /**
     * Start pool of threads
     * Begin the process of serve
     */
    private void openQueues() {

        pool.execute(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    if (!isEmpty()) {
                        exit.process();
                        Thread.sleep(EXIT_TIMEOUT);
                    }
                }
            }
            catch (InterruptedException e) {
                LOG.error("Exit thread was interrupted");
            }
        });

        cashiers.forEach(cashier -> pool.execute(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    if (!isEmpty()) {
                        Buyer buyer = cashier.process();

                        if (buyer != null) {
                            products.sellAll(buyer.getCart().getProducts(), buyer);
                        }

                        Thread.sleep(CASHIERS_TIMEOUT);
                    }
                }
            }
            catch (InterruptedException e) {
                LOG.error("Cashier #" + cashier.getNumber() + " thread was interrupted");
            }
        })
        );
    }

    /**
     * Await all {@link Cashier}s finish their job or pass {@link #SHOP_TIMEOUT} seconds
     * Then report about all {@link SoldProduct}s
     */
    public void close() {
        try {
            if (!pool.awaitTermination(SHOP_TIMEOUT, TimeUnit.SECONDS)) {
                pool.shutdownNow();
            }
        }
        catch (InterruptedException e) {
            LOG.error("Queues' threads were interrupted");
        }

        LOG.info("Shop is closed");

        report();

        if (!save())
            LOG.error("Cannot save shop");
    }

    /**
     * Print info about {@link SoldProduct}s
     */
    private void report() {
        LOG.info("All products more expensive than " + X_VALUE);
        products.getAll().parallelStream().
                filter(product -> product.getPrice() > X_VALUE).
                forEach(product -> LOG.info(product.getTitle() + " --- " + product.getPrice()));

        LOG.info("All products expire within " + Y_VALUE + " days");
        products.getAll().parallelStream().
                filter(product -> !product.isExpirable() && product.willExpireWithin(Y_VALUE)).
                forEach(product -> LOG.info(product.getTitle() + " --- " + product.getExpireDate()));
    }

    /**
     * @return false if there is no any {@link Buyer} in all {@link Cashier}s
     */
    public boolean isEmpty() {
        return cashiers.parallelStream().
                allMatch(Queueable::isEmpty);
    }

    /**
     * Calculate discounts for every buyer who bought something within {@link #EXPIRE_DAYS_FOR_DISCOUNTS} days
     * If overall price exceeds 5 * {@link #X_VALUE}, buyers receive {@link #DEFAULT_DISCOUNT} discount
     * Lastly, pass discounts to all {@link Cashier}s
     */
    public void updateDiscounts() {
        Map<String, Double> discounts = products.getAll().parallelStream().
                filter(product -> product.soldLastDays(EXPIRE_DAYS_FOR_DISCOUNTS)).
                collect(
                        groupingBy(SoldProduct::getBuyerId,
                                summingDouble(SoldProduct::getPrice)));

        discounts.entrySet().parallelStream()
                .forEach(entry -> entry.setValue(entry.getValue() >= 5 * X_VALUE ? DEFAULT_DISCOUNT : 0d));

        cashiers.forEach(cashier -> cashier.setDiscounts(discounts));
    }

    public boolean save() {
        return Serializer.serialize(file, this);
    }

    public static Shop load() {
        Shop shop = Serializer.deserialize(file, Shop.class);

        if (shop == null)
            return new Shop();
        else
            return shop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Shop))
            return false;

        Shop shop = (Shop) o;
        return cashiers.equals(shop.cashiers) &&
                exit.equals(shop.exit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cashiers, exit);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [cashiers number: " + cashiers.size() + "]";
    }
}
