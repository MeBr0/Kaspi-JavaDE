package kz.kaspi.shop.queue;

import kz.kaspi.person.Buyer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Class for exiting {@link Cashier} without any {@link kz.kaspi.shop.Cart}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Exit implements Queueable<Buyer> {

    private Deque<Buyer> buyers;

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
    public void processFirst() {
        Buyer buyer = buyers.removeFirst();

        System.out.println(buyer + " processed to exit");
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
        return true;
    }
}
