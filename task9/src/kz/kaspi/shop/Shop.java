package kz.kaspi.shop;

import kz.kaspi.person.Buyer;
import kz.kaspi.person.error.CartNotFoundException;
import kz.kaspi.shop.product.sold.SoldProduct;
import kz.kaspi.shop.product.sold.SoldProductList;
import kz.kaspi.shop.queue.Cashier;
import kz.kaspi.shop.queue.Cashier.State;
import kz.kaspi.shop.queue.Exit;
import kz.kaspi.shop.queue.Queueable;
import kz.kaspi.shop.queue.error.NoOpenCashiersException;
import kz.kaspi.util.Logger;
import kz.kaspi.util.Serializer;

import java.io.Serializable;
import java.util.*;

/**
 * Shop class with cashiers and exit classes
 * Processes people with {@link kz.kaspi.shop.product.Product}s
 *
 * @author A.Yergali
 * @version 2.0
 */
public class Shop implements Serializable {

    private static final Logger LOG = Logger.getInstance();
    private static final String file = "shop.out";

    private static final double X_VALUE = 344.2;
    private static final int Y_VALUE = 7;
    private static final int EXPIRE_DAYS_FOR_DISCOUNTS = 10;
    private static final double DEFAULT_DISCOUNT = 5;

    private List<Cashier> cashiers;
    private Queueable<Buyer> exit;

    private SoldProductList products;

    {
        cashiers = new ArrayList<>();
        exit = new Exit();
        products = SoldProductList.empty();

        addCashier(new Cashier(1));
        addCashier(new Cashier(2, State.OFF));
        addCashier(new Cashier(3));
    }

    private Shop() { }

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

    public void addCashier(Cashier cashier) {
        cashiers.add(cashier);
    }

    /**
     * Process all queue in {@link Shop}
     * Gather all {@link SoldProduct}s from {@link Cashier}s
     * Write little {@link #report(SoldProductList)} about sold products
     */
    public void processAll() {
        exit.processAll();

        SoldProductList list = SoldProductList.empty();

        cashiers.stream().
                map(Cashier::sellAll).
                forEach(list::addAll);

        report(list);

        products.addAll(list);
    }

    private void report(SoldProductList list) {
        System.out.println("All products more expensive than " + X_VALUE);
        list.getAll().stream().
                filter(product -> product.getPrice() > X_VALUE).
                forEach(System.out::println);

        System.out.println("All products expire within " + Y_VALUE + " days");
        list.getAll().stream().
                filter(product -> !product.isExpirable() && product.willExpireWithin(Y_VALUE)).
                forEach(System.out::println);
    }

    public boolean isEmpty() {
        return cashiers.stream().
                allMatch(Queueable::isEmpty);
    }

    /**
     * Calculate discounts for every buyer who bought something within {@link #EXPIRE_DAYS_FOR_DISCOUNTS} days
     * If overall price exceeds 5 * {@link #X_VALUE}, buyers receive {@link #DEFAULT_DISCOUNT} discount
     * Lastly, pass discounts to all {@link Cashier}s
     */
    public void updateDiscounts() {
        Map<String, Double> discounts = new HashMap<>();

        products.getAll().stream().
                filter(product -> product.soldLastDays(EXPIRE_DAYS_FOR_DISCOUNTS)).
                forEach(product -> {
                    discounts.computeIfPresent(product.getBuyerId(), (key, value) -> value + product.getPrice());
                    discounts.putIfAbsent(product.getBuyerId(), product.getPrice());
                });

        discounts.keySet().
                forEach(buyerId ->
                        discounts.replace(buyerId, discounts.get(buyerId) >= 5 * X_VALUE ? DEFAULT_DISCOUNT : 0d));

        cashiers.forEach(cashier -> cashier.setDiscounts(discounts));
    }

    public boolean save() {
        return Serializer.serialize(file, this);
    }

    public static Shop load() {
        Shop shop = Serializer.deserialize(file, Shop.class);

        if (shop == null) {
            return new Shop();
        }
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
