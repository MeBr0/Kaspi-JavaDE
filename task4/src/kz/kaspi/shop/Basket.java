package kz.kaspi.shop;

import kz.kaspi.shop.product.Product;
import kz.kaspi.shop.product.type.Expirable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Singleton collection class for {@link Product}
 *
 * Have {@link #add(Product) to add product into basket}
 * and {@link #getTotalCost()} for calculating total cost of products
 * and {@link #getNotExpiredProducts()} for getting products which is {@link Expirable} but not expired
 * and {@link #getInstance()} for getting single instance of {@link Basket} (BECAUSE IT IS SINGLETON)
 *
 * @author A.Yergali
 * @version 2.0
 */
public class Basket {

    private static Basket basket;
    private List<Product> products;

    private Basket() {

    }

    {
        products = new ArrayList<>();
    }

    public static Basket getInstance() {
        if (basket == null) {
            basket = new Basket();
        }

        return basket;
    }

    /**
     * Add product to basket
     */
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
     * Return only product that have expiration date
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

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Basket))
            return false;

        Basket basket = (Basket) o;
        return products.equals(basket.products);
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
