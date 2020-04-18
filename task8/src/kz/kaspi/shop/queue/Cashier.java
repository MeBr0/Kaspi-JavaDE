package kz.kaspi.shop.queue;

import kz.kaspi.person.Buyer;
import kz.kaspi.shop.cart.Cart;
import kz.kaspi.util.Logger;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Class for processing and calculating total cost of {@link kz.kaspi.shop.product.Product}s of {@link Buyer}
 *
 * @author A.Yergali
 * @version 4.0
 */
public class Cashier implements Queueable<Buyer>, Serializable {

    private final int number;
    private State state;
    private Deque<Buyer> buyers;
    private final static Logger LOG = Logger.getInstance();

    {
        state = State.ON;
        buyers = new LinkedList<>();
    }

    public Cashier(int number, State state) {
        this.number = number;
        this.state = state;
    }

    public Cashier(int number) {
        this.number = number;
    }

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

        double result = (int) (cart.getTotalCost() * 100) / 100.0;  // Two decimal digits
        LOG.info(buyer.getClass().getSimpleName() + " with id " + buyer.getId() + " processed to " + number +
                " cashier (Total cost: " + result + ")");
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
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Cashier))
            return false;

        Cashier cashier = (Cashier) o;
        return number == cashier.number &&
                state == cashier.state &&
                buyers.equals(cashier.buyers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, state, buyers);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [number: " + number + ", " + state.getValue() + "]";
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
