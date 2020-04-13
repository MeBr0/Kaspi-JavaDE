package kz.kaspi.shop;

import kz.kaspi.shop.product.Product;
import kz.kaspi.shop.product.type.Expirable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Collection class for {@link Product}
 *
 * Have {@link #add(Product) to add product into basket}
 * and {@link #getTotalCost()} for calculating total cost of products
 * and {@link #getNotExpiredProducts()} for getting products which is {@link Expirable} but not expired
 * and {@link #count()} for getting count of products
 * and {@link #clear()} for clearing all products in {@link SubCart}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class SubCart<T extends Product> {

    private List<T> products;

    {
        products = new ArrayList<>();
    }

    /**
     * Add product to basket
     */
    public void add(T product) {
        products.add(product);
    }

    /**
     * Get total cost of all products in basket
     */
    public double getTotalCost() {
        return products.stream().
                mapToDouble(T::getPrice).
                sum();
    }

    /**
     * Return only product that have expiration date
     * and expire date did not passed
     */
    public List<T> getNotExpiredProducts() {
        LocalDate now = LocalDate.now();

        return products.stream().
                filter(product -> product instanceof Expirable).
                filter(product -> ((Expirable) product).getExpireDate().compareTo(now) >= 0).
                collect(Collectors.toList());
    }

    /**
     * Return count of products
     */
    public int count() {
        return products.size();
    }

    /**
     * Clear basket from all products
     */
    public void clear() {
        products.clear();
    }

    public List<T> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof SubCart))
            return false;

        SubCart<?> subCart = (SubCart<?>) o;
        return products.equals(subCart.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [product count: " + products.size() + "]";
    }
}
