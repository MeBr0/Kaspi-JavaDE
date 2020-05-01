package kz.kaspi.shop.product.other;

import kz.kaspi.shop.product.type.Expirable;
import kz.kaspi.shop.product.Product;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Example of class which is not {@link kz.kaspi.shop.product.food.Food}
 * but {@link Expirable}
 *
 * @author A.Yergali
 * @version 2.0
 */
public class CleaningAgent extends Product implements Expirable, Serializable {

    private static final int EXPIRE_DAYS = 365 * 2;

    public CleaningAgent(double price, String title, LocalDate issueDate) {
        super(price, title, issueDate);
    }

    @Override
    public int getExpireDays() {
        return EXPIRE_DAYS;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + super.toString() +
                ", expire date: " + getExpireDate().toString() +  "]";
    }
}
