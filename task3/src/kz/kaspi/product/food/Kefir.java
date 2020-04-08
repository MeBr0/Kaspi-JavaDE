package kz.kaspi.product.food;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Class for kefirs
 * Have fat level
 */
public class Kefir extends Food {

    private float fat;

    public Kefir(double price, String title, LocalDate issueDate, int expireDays, float fat) {
        super(price, title, issueDate, expireDays);
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

        if (!(o instanceof Kefir))
            return false;

        if (!super.equals(o))
            return false;

        Kefir milk = (Kefir) o;
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
