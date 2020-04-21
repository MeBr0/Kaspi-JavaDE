package kz.kaspi.shop.queue;

import kz.kaspi.person.Buyer;
import kz.kaspi.shop.cart.Cart;
import kz.kaspi.util.Logger;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Class for exiting {@link Cashier} without any {@link Cart}
 *
 * @author A.Yergali
 * @version 3.0
 */
public class Exit implements Queueable<Buyer>, Serializable {

    private Deque<Buyer> buyers;

    private final static Logger LOG = Logger.getInstance();

    {
        buyers = new LinkedList<>();
    }

    @Override
    public Deque<Buyer> getQueue() {
        return buyers;
    }

    @Override
    public void add(Buyer buyer) {
        buyers.addLast(buyer);
    }

    @Override
    public Buyer processFirst() {
        Buyer buyer = buyers.removeFirst();

        LOG.info(buyer.getClass().getSimpleName() +  " with id " + buyer.getId() + " processed to exit");

        return buyer;
    }

    @Override
    public int size() {
        return buyers.size();
    }

    @Override
    public boolean isEmpty() {
        return buyers.isEmpty();
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public boolean isNotExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Exit))
            return false;

        Exit exit = (Exit) o;
        return buyers.equals(exit.buyers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyers);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
