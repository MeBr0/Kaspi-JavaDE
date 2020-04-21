package kz.kaspi.shop.queue;

import kz.kaspi.person.Buyer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Interface for queueing processed in shop
 *
 * @param <T> For now only {@link Buyer}
 * @author A.Yergali
 * @version 2.0
 */
public interface Queueable<T> {

    Deque<T> getQueue();
    void add(T t);
    T processFirst();
    int size();
    boolean isEmpty();
    boolean isOpen();

    /**
     * Since now queues can be only {@link Cashier} or {@link Exit}
     * there is special method for determine this
     */
    boolean isNotExit();

    default Deque<T> processAll() {
        Deque<T> deque = new LinkedList<>();

        while (!isEmpty())
            deque.add(processFirst());

        return deque;
    }
}
