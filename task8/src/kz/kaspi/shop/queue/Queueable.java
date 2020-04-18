package kz.kaspi.shop.queue;

import kz.kaspi.person.Buyer;

import java.io.Serializable;
import java.util.Deque;

/**
 * Interface for queueing processed in shop
 *
 * @param <T> For now only {@link Buyer}
 * @author A.Yergali
 * @version 1.0
 */
public interface Queueable<T> {

    Deque<T> getQueue();
    void add(T t);
    void processFirst();
    int size();
    boolean isEmpty();
    boolean isOpen();

    /**
     * Since now queues can be only {@link Cashier} or {@link Exit}
     * there is special method for determine this
     */
    boolean isNotExit();

    default void processAll() {
        while (!isEmpty())
            processFirst();
    }
}
