package kz.kaspi.shop;

import kz.kaspi.person.Buyer;
import kz.kaspi.person.error.CartNotFoundException;
import kz.kaspi.shop.queue.Cashier;
import kz.kaspi.shop.queue.Exit;
import kz.kaspi.shop.queue.Queueable;
import kz.kaspi.shop.queue.error.NoOpenCashiersException;
import kz.kaspi.util.Logger;
import kz.kaspi.util.Serializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Shop class with cashiers and exit classes
 * Processes people with {@link kz.kaspi.shop.product.Product}s
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Shop implements Serializable {

    private static final Logger LOG = Logger.getInstance();
    private static final String file = "shop.out";

    private List<Queueable<Buyer>> cashiers;
    private Queueable<Buyer> exit;

    {
        cashiers = new ArrayList<>();
        exit = new Exit();

        addCashier(new Cashier(1));
        addCashier(new Cashier(2, Cashier.State.OFF));
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

    public void addCashier(Queueable<Buyer> cashier) {
        cashiers.add(cashier);
    }

    public void processAll() {
        exit.processAll();
        cashiers.forEach(Queueable::processAll);
    }

    public boolean isEmpty() {
        return cashiers.stream().
                allMatch(Queueable::isEmpty);
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
