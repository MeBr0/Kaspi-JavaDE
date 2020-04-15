package kz.kaspi.shop.queue;

import kz.kaspi.person.Buyer;
import kz.kaspi.shop.Cart;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Class for processing and calculating total cost of {@link kz.kaspi.shop.product.Product}s of {@link Buyer}
 *
 * @author A.Yergali
 * @version 3.0
 */
public class Cashier implements Queueable<Buyer> {

    private State state;
    private Deque<Buyer> buyers;

    {
        state = State.ON;
        buyers = new LinkedList<>();
    }

    public Cashier(State state) {
        this.state = state;
    }

    public Cashier() { }

    @Override
    public Deque<Buyer> getQueue() {
        return buyers;
    }

    @Override
    public void add(Buyer buyer) {
        buyers.addLast(buyer);
    }

    /**
     * Method for calculating total price of {@link Cart} of first {@link Buyer}
     */
    @Override
    public void processFirst() {
        Buyer buyer = buyers.removeFirst();
        Cart cart = buyer.getCart();

        double result = cart.getTotalCost();
        System.out.println(buyer + " processed with " + result);
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
        return state == State.ON;
    }

    @Override
    public boolean isNotExit() {
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + state.getValue() + "]";
    }

    public enum State {

        ON("works"),
        OFF("do not work");

        private final String value;

        State(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
