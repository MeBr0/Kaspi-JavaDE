package kz.kaspi.shop;

import kz.kaspi.shop.product.Product;
import kz.kaspi.shop.product.type.Expirable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Collection class for {@link Product}
 *
 * Have {@link #add(Product)} to add product into basket
 * and {@link #getTotalCost()} for calculating total cost of products
 * and {@link #getNotExpiredProducts()} for getting products which is {@link Expirable} but not expired
 * and {@link #count()} for getting count of products
 * and {@link #clear()} for clearing all products in {@link Cart}
 *
 * @author A.Yergali
 * @version 5.0
 */
public class Cart implements Serializable {

    private final List<Product> products;

    private Cart() {

    }

    {
        products = new ArrayList<>();
    }

    public static Cart getInstance() {
        return new Cart();
    }

    public void add(Product product) {
        products.add(product);
    }

    /**
     * Get total cost of all products in basket
     */
    public double getTotalCost() {
        return products.stream().
                mapToDouble(Product::getPrice).
                sum();
    }

    /**
     * Return only products that have expiration date
     * and expire date did not passed
     */
    public List<Product> getNotExpiredProducts() {
        LocalDate now = LocalDate.now();

        return products.stream().
                filter(product -> product instanceof Expirable).
                filter(product -> ((Expirable) product).getExpireDate().compareTo(now) >= 0).
                collect(Collectors.toList());
    }

    /**
     * Clear basket from all products
     */
    public void clear() {
        products.clear();
    }

    /**
     * Return count of products
     */
    public int count() {
        return products.size();
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Cart))
            return false;

        Cart cart = (Cart) o;
        return products.equals(cart.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [product count: " + count() + "]";
    }
}
