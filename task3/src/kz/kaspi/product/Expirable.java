package kz.kaspi.product;

import java.time.LocalDate;

/**
 * Interface for expirable products i.e. have expire date
 *
 * @author A.Yergalo
 * @version 1.0
 */
public interface Expirable {
    LocalDate getExpireDate();
}
