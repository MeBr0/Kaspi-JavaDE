package kz.kaspi.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Collection class for {@link Product}
 *
 * Have {@link Basket#add(Product) to add product into basket}
 * and {@link Basket#getTotalCost()} for calculating total cost of products
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Basket {

    private List<Product> products;

    {
        products = new ArrayList<>();
    }

    public void add(Product product) {
        products.add(product);
    }

    public double getTotalCost() {
        return products.stream().
                mapToDouble(Product::getPrice).
                sum();
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
