package kz.kaspi.shop.product.food;

import kz.kaspi.shop.product.type.Expirable;
import kz.kaspi.shop.product.Product;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Base abstract class for food
 * Have fields expire days and issue Date
 *
 * @author A.Yergali
 * @version 3.0
 */
public abstract class Food extends Product implements Expirable, Serializable {

    private int expireDays;

    public Food(double price, String title, LocalDate issueDate, int expireDays) {
        super(price, title, issueDate);
        this.expireDays = expireDays;
    }

    @Override
    public LocalDate getIssueDate() {
        return super.getIssueDate();
    }

    @Override
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
