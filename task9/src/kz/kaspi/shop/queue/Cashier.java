package kz.kaspi.shop.queue;

import kz.kaspi.person.Buyer;
import kz.kaspi.shop.cart.Cart;
import kz.kaspi.shop.product.sold.SoldProductList;
import kz.kaspi.util.Logger;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
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
    private Map<String, Double> discounts;

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
     * Method for selling all {@link Buyer}s {@link kz.kaspi.shop.product.Product}s in {@link Cart}
     */
    public SoldProductList sellAll() {
        SoldProductList list = SoldProductList.empty();

        processAll().forEach(buyer -> list.sellAll(buyer.getCart().getProducts(), buyer));

        return list;
    }

    @Override
    public Buyer processFirst() {
        Buyer buyer = buyers.removeFirst();
        Cart cart = buyer.getCart();

        double discount = discounts.getOrDefault(buyer.getId(), 0d);
        double totalCost = (int) (cart.getTotalCost() * 100) / 100.0;   // Two decimal digits

        if (discount <= 0 || discount > 100) {                          // Discount validations
            LOG.info(buyer.getClass().getSimpleName() + " with id " + buyer.getId() + " (" + buyer.getName() +
                    ") processed to " + number + " cashier (Total cost: " + cart.getTotalCost() + ") items: " + cart.getProducts().size());
        }
        else {
            double discountCost = totalCost * (1 - discount / 100);
            discountCost = (int) (discountCost * 100) / 100.0;          // Two decimal digits

            LOG.info(buyer.getClass().getSimpleName() + " with id " + buyer.getId() + " (" + buyer.getName() +
                    ") processed to " + number + " cashier (Discount cost: " + discountCost +
                    ", total cost: " + totalCost + ")");
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
