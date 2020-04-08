package kz.kaspi.product.food;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Class for milks
 * Have fat level
 */
public class Milk extends Food {

    private float fat;

    public Milk(double price, String title, LocalDate shelfLife, float fat) {
        super(price, title, shelfLife);
        this.fat = fat;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Milk))
            return false;

        if (!super.equals(o))
            return false;

        Milk milk = (Milk) o;
        return milk.fat == fat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fat);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + super.toString() + ", fat: " + fat + "]";
    }
}
