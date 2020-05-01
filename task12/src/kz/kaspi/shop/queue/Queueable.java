package kz.kaspi.shop.queue;

import kz.kaspi.person.Buyer;

import java.util.Queue;

/**
 * Interface for queueing processed in shop
 *
 * @param <T> For now only {@link Buyer}
 * @author A.Yergali
 * @version 3.0
 */
public interface Queueable<T> {

    Queue<T> getQueue();
    void add(T t);
    T process();
    int size();
    boolean isEmpty();
    boolean isOpen();

    /**
     * Since now queues can be only {@link Cashier} or {@link Exit}
     * there is special method for determine this
     */
    boolean isNotExit();
}
