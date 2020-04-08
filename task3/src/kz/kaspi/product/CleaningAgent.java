package kz.kaspi.product;

import java.time.LocalDate;

/**
 * Example of class which is not {@link kz.kaspi.product.food.Food}
 * but {@link Expirable}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class CleaningAgent extends Product implements Expirable {

    private static final long EXPIRE_DAYS = 365 * 2;

    public CleaningAgent(double price, String title, LocalDate issueDate) {
        super(price, title, issueDate);
    }

    @Override
    public LocalDate getExpireDate() {
        return getIssueDate().plusDays(EXPIRE_DAYS);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + super.toString() +
                ", expire date: " + getExpireDate().toString() +  "]";
    }
}
