package kz.kaspi.product.food;

import kz.kaspi.product.Expirable;
import kz.kaspi.product.Product;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Base abstract class for food
 * Have fields expire days and issue Date
 *
 * @author A.Yergali
 * @version 2.0
 */
public abstract class Food extends Product implements Expirable {

    private int expireDays;

    public Food(double price, String title, LocalDate issueDate, int expireDays) {
        super(price, title, issueDate);
        this.expireDays = expireDays;
    }

    @Override
    public LocalDate getExpireDate() {
        return getIssueDate().plusDays(expireDays);
    }

    public int getExpireDays() {
        return expireDays;
    }

    public void setExpireDays(int expireDays) {
        this.expireDays = expireDays;
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
        return expireDays == food.expireDays;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), expireDays);
    }

    @Override
    public String toString() {
        return super.toString() + ", expire date: " + getExpireDate().toString();
    }
}
