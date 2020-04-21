package kz.kaspi.person.error;

/**
 * Exception thrown then cart of {@link kz.kaspi.person.Buyer} is null
 *
 * @author A.Yergali
 * @version 1.0
 */
public class CartNotFoundException extends Exception {

    public CartNotFoundException(String cause) {
        super(cause);
    }
}
