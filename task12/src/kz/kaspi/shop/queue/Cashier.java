package kz.kaspi.shop.queue;

import kz.kaspi.person.Buyer;
import kz.kaspi.shop.Cart;
import kz.kaspi.util.Logger;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class for processing and calculating total cost of {@link kz.kaspi.shop.product.Product}s of {@link Buyer}
 *
 * @author A.Yergali
 * @version 6.0
 */
public class Cashier implements Queueable<Buyer>, Serializable {

    private final int number;
    private State state;
    private final Queue<Buyer> buyers;
    private Map<String, Double> discounts;

    private final static Logger LOG = Logger.getInstance();

    {
        buyers = new ConcurrentLinkedQueue<>();
    }

    private Cashier(int number, State state) {
        this.number = number;
        this.state = state;
    }

    /**
     * Creates open cashier
     * @param number (must be unique)
     */
    public static Cashier open(int number) {
        return new Cashier(number, State.ON);
    }

    /**
     * Creates closed cashier
     * @param number (must be unique)
     */
    public static Cashier closed(int number) {
        return new Cashier(number, State.OFF);
    }

    @Override
    public Queue<Buyer> getQueue() {
        return buyers;
    }

    @Override
    public void add(Buyer buyer) {
        buyers.offer(buyer);
        LOG.info(String.format("%s (%s, id: %s) processed to %s cashier",
                buyer.getName(), buyer.getClass().getSimpleName(), buyer.getId(), number));
    }

    @Override
    public Buyer process() {
        Buyer buyer = buyers.poll();

        if (buyer == null)
            return null;

        Cart cart = buyer.getCart();

        double discount = discounts.getOrDefault(buyer.getId(), 0d);
        double totalCost = (int) (cart.getTotalCost() * 100) / 100.0;

        if (discount <= 0 || discount > 100) {                          // Discount validations
            LOG.info(String.format(
                    "%s (%s, id: %s) served at %d cashier (Total: %.2f, items: %d)",
                    buyer.getName(), buyer.getClass().getSimpleName(), buyer.getId(), number, cart.getTotalCost(),
                    cart.getProducts().size()));
        }
        else {
            double newCost = totalCost * (1 - discount / 100);
            double discountCost = totalCost - newCost;

            LOG.info(String.format(
                    "%s (%s, id: %s) served at %d cashier (Total: %.2f, discount: %.2f, items: %d)",
                    buyer.getName(), buyer.getClass().getSimpleName(), buyer.getId(), number, newCost, discountCost,
                    cart.getProducts().size()));
        }

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
        return state == State.ON;
    }

    @Override
    public boolean isNotExit() {
        return true;
    }

    public int getNumber() {
        return number;
    }

    public void setDiscounts(Map<String, Double> discounts) {
        this.discounts = discounts;
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
