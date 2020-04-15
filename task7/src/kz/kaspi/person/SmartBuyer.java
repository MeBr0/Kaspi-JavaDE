package kz.kaspi.person;

import kz.kaspi.person.error.CartNotFoundException;
import kz.kaspi.shop.queue.Queueable;
import kz.kaspi.shop.queue.error.NoOpenCashiersException;

import java.util.List;

/**
 * Smart version of regular {@link Buyer} with improved {@link #chooseQueue(List)}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class SmartBuyer extends Buyer {

    public SmartBuyer(String name) {
        super(name);
    }

    @Override
    public void chooseQueue(List<Queueable<Buyer>> cashiers) throws CartNotFoundException, NoOpenCashiersException {
        if (getCart() == null)
            throw new CartNotFoundException(getId() + " buyer's cart is null");

        int min = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < cashiers.size(); i++) {
            Queueable<Buyer> queue = cashiers.get(i);

            int productCount = queue.getQueue().stream().
                    mapToInt(buyer -> buyer.getCart().getProducts().size()).
                    sum();

            if (queue.isOpen() && queue.isNotExit() && productCount < min) {
                min = productCount;
                index = i;
            }
        }

        if (index != -1)
            cashiers.get(index).add(this);
        else
            throw new NoOpenCashiersException("No open cashiers");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [name: " + getName() + ", id: " + getId() + ", cart: " + getCart() + "]";
    }
}
