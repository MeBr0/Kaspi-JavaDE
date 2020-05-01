package kz.kaspi.person;

import kz.kaspi.person.error.CartNotFoundException;
import kz.kaspi.shop.queue.Cashier;
import kz.kaspi.shop.queue.Queueable;
import kz.kaspi.shop.queue.error.NoOpenCashiersException;

import java.io.Serializable;
import java.util.List;

/**
 * Lazy version of regular {@link Buyer} with own {@link #chooseQueue(List)}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class LazyBuyer extends Buyer implements Serializable {

    public LazyBuyer(String id, String name) {
        super(id, name);
    }

    @Override
    public void chooseQueue(List<Cashier> cashiers) throws CartNotFoundException, NoOpenCashiersException {
        if (getCart() == null)
            throw new CartNotFoundException(getId() + " buyer's cart is null");

        Queueable<Buyer> first = null, second = null;

        for (Queueable<Buyer> queue : cashiers) {
            if (queue.isOpen() && queue.isNotExit()) {
                if (first == null) {
                    first = queue;
                }
                else {
                    second = queue;
                    break;
                }
            }
        }

        if (first != null) {
            if (second != null) {
                if (first.size() > second.size())
                    second.add(this);
                else
                    first.add(this);
            }
            else {
                first.add(this);
            }
        }
        else {
            throw new NoOpenCashiersException("No open cashiers");
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [name: " + getName() + ", id: " + getId() + ", cart: " + getCart() + "]";
    }
}
