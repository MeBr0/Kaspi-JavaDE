package kz.kaspi.person;

import kz.kaspi.shop.queue.Queueable;

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
    public void chooseQueue(List<Queueable<Buyer>> cashiers) {
        if (getCart() == null)
            throw new NullPointerException(getId() + " buyer's cart is null");

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
            System.err.println("Cannot find any open cashiers");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [name: " + getName() + ", id: " + getId() + ", cart: " + getCart() + "]";
    }
}
