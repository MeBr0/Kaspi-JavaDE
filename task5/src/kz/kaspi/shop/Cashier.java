package kz.kaspi.shop;

import kz.kaspi.Buyer;

/**
 * Class for calculating price from {@link Cart}
 *
 * {@link #calculate(Buyer)} return total cost of {@link Buyer's} {@link Cart}
 *
 * @author A.Yergali
 * @version 2.0
 */
public class Cashier {

    private State state;

    {
        state = State.ON;
    }

    public Cashier(State state) {
        this.state = state;
    }

    public Cashier() { }

    /**
     * Method for calculating total price of {@link Cart} of {@link Buyer}
     * Do not work if cashier closed or cart not found, return -1
     */
    public double calculate(Buyer buyer) {
        System.out.println("Cashier " + state.getValue());

        if (state == State.ON) {
            Cart cart = buyer.getCart();

            if (cart != null)
                return cart.getTotalCost();

            else
                System.err.println("Cart is null");
        }

        else
            System.err.println("Cashier is closed!");

        return -1;
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
