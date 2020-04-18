package kz.kaspi.shop.queue.error;

/**
 * Exception thrown then there is no open cashiers in list
 *
 * @author A.Yergali
 * @version 1.0
 */
public class NoOpenCashiersException extends Exception {

    public NoOpenCashiersException(String cause) {
        super(cause);
    }
}
