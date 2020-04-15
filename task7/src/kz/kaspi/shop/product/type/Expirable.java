package kz.kaspi.shop.product.type;

import java.time.LocalDate;

/**
 * Interface for expirable products i.e. have expire date
 *
 * @author A.Yergali
 * @version 2.0
 */
public interface Expirable {

    LocalDate getIssueDate();
    int getExpireDays();

    default LocalDate getExpireDate() {
        return getIssueDate().plusDays(getExpireDays());
    }
}
