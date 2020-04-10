package kz.kaspi.shop;

/**
 * Class for calculating price from {@link Basket}
 *
 * {@link #calculate(Basket)} return total cost of products in basket
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Cashbox {

    private State state;

    {
        state = State.ON;
    }

    public Cashbox(State state) {
        this.state = state;
    }

    public Cashbox() { }

    /**
     * Method for calculating total price from basket
     * Do not work if cashbox closed, return -1
     */
    public double calculate(Basket basket) {
        System.out.println("Cashbox " + state.getValue());

        if (state == State.ON)
            return basket.getTotalCost();

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
