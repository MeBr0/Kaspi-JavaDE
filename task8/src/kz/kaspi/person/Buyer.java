package kz.kaspi.person;

import kz.kaspi.person.error.CartNotFoundException;
import kz.kaspi.shop.cart.Cart;
import kz.kaspi.shop.product.Product;
import kz.kaspi.shop.queue.Queueable;
import kz.kaspi.shop.queue.error.NoOpenCashiersException;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Class for person who buy {@link Product} and add it to their {@link Cart}
 * Lazy initialization of {@link Cart} by {@link #init()}
 *
 * @author A.Yergali
 * @version 2.0
 */
public class Buyer implements Serializable {

    private String name;
    private final String id;
    private Cart cart;

    {
        id = UUID.randomUUID().toString();
    }

    public Buyer(String name) {
        this.name = name;
    }

    public void chooseQueue(List<Queueable<Buyer>> cashiers) throws CartNotFoundException, NoOpenCashiersException {
        if (cart == null)
            throw new CartNotFoundException(getId() + " buyer's cart is null");

        int min = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < cashiers.size(); i++) {
            Queueable<Buyer> queue = cashiers.get(i);
            if (queue.isOpen() && queue.isNotExit() && queue.size() < min) {
                min = queue.size();
                index = i;
            }
        }

        if (index != -1)
            cashiers.get(index).add(this);
        else
            throw new NoOpenCashiersException("No open cashiers");
    }

    public void add(Product product) {
        init();

        cart.add(product);
    }

    private void init() {
        if (cart == null)
            cart = Cart.getInstance();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Buyer))
            return false;

        Buyer buyer = (Buyer) o;
        return name.equals(buyer.name) &&
                id.equals(buyer.id) &&
                cart.equals(buyer.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, cart);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [name: " + name + ", id: " + id + ", cart: " + cart + "]";
    }
}
