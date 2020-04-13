package kz.kaspi;

import kz.kaspi.shop.Cart;
import kz.kaspi.shop.product.Product;

import java.util.Objects;

/**
 * Class for person who buy {@link Product} and add it to him/his {@link Cart}
 * Lazy initialization of {@link Cart} by {@link #init()}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Buyer {

    private String name;
    private Cart cart;

    public Buyer(String name) {
        this.name = name;
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
                cart.equals(buyer.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cart);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [name: " + name + ", cart: " + cart + "]";
    }
}
