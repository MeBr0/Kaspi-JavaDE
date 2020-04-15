package kz.kaspi.shop.product.food;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Builder class for sausages
 * Have own fields with enums {@link MeatType}
 *
 * @author A.Yergali
 * @version 2.0
 */
public class Sausage extends Food {

    private MeatType meat;

    public Sausage(Builder builder) {
        super(builder.price, builder.title, builder.issueDate, builder.expireDays);
        this.meat = builder.meat;
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

    public static class Builder {

        private double price;
        private String title;
        private LocalDate issueDate;
        private int expireDays;
        private MeatType meat;

        private Builder() { }

        public static Builder getInstance() {
            return new Builder();
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder issueDate(LocalDate issueDate) {
            this.issueDate = issueDate;
            return this;
        }

        public Builder expireDays(int expireDays) {
            this.expireDays = expireDays;
            return this;
        }

        public Builder meatType(MeatType meat) {
            this.meat = meat;
            return this;
        }

        public Sausage build() {
            return new Sausage(this);
        }
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
