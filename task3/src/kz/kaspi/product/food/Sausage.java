package kz.kaspi.product.food;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Class for sausages
 * Have own fields with enums {@link MeatType}
 */
public class Sausage extends Food {

    private MeatType meat;

    public Sausage(double price, String title, LocalDate issueDate, int expireDays, MeatType meat) {
        super(price, title, issueDate, expireDays);
        this.meat = meat;
    }

    public MeatType getMeat() {
        return meat;
    }

    public void setMeat(MeatType meat) {
        this.meat = meat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Sausage))
            return false;

        if (!super.equals(o))
            return false;

        Sausage sausage = (Sausage) o;
        return meat == sausage.meat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), meat);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + super.toString() + ", meat type: " + meat.value + "]";
    }

    public enum MeatType {

        LIVER("Liver"),
        BLOOD("Blood"),
        HALF_SMOKED("Half-smoked"),
        SMOKED("Smoked"),
        RAW_SMOKED("Row-smoked"),
        COOKED("Cooked");

        private final String value;

        MeatType(String value) {
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
