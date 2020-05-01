package kz.kaspi.shop.queue;

import kz.kaspi.person.Buyer;
import kz.kaspi.shop.Cart;
import kz.kaspi.util.Logger;

import java.io.Serializable;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class for exiting {@link Cashier}s without any {@link Cart}
 *
 * @author A.Yergali
 * @version 4.0
 */
public class Exit implements Queueable<Buyer>, Serializable {

    private final Queue<Buyer> buyers;

    private final static Logger LOG = Logger.getInstance();

    {
        buyers = new ConcurrentLinkedQueue<>();
    }

    @Override
    public Queue<Buyer> getQueue() {
        return buyers;
    }

    @Override
    public void add(Buyer buyer) {
        buyers.offer(buyer);
    }

    @Override
    public Buyer process() {
        Buyer buyer = buyers.poll();

        if (buyer == null)
            return null;

        LOG.info(String.format("%s (%s, id: %s) exited with empty cart",
                buyer.getName(),  buyer.getClass().getSimpleName(), buyer.getId()));

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
