package kz.kaspi.product.food;

import kz.kaspi.product.Product;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Base abstract class for food
 * Have field for shelf life
 */
public abstract class Food extends Product {

    private LocalDate shelfLife;

    public Food(double price, String title, LocalDate shelfLife) {
        super(price, title);
        this.shelfLife = shelfLife;
    }

    public LocalDate getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(LocalDate shelfLife) {
        this.shelfLife = shelfLife;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Food))
            return false;

        if (!super.equals(o))
            return false;

        Food food = (Food) o;
        return shelfLife.equals(food.shelfLife);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shelfLife);
    }

    @Override
    public String toString() {
        return super.toString() + ", shelf life: " + shelfLife.toString();
    }
}
