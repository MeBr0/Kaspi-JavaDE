package kz.kaspi.shop.product.sold;

import kz.kaspi.person.Buyer;
import kz.kaspi.shop.product.Product;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Collection class for {@link SoldProduct}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class SoldProductList implements Serializable {

    private final List<SoldProduct> products;

    private SoldProductList() { }

    {
        products = new CopyOnWriteArrayList<>();
    }

    /**
     * Creates new empty list
     */
    public static SoldProductList empty() {
        return new SoldProductList();
    }

    public List<SoldProduct> getAll() {
        return products;
    }

    public void sellAll(List<Product> products, Buyer buyer) {
        products.forEach(product -> sell(product, buyer));
    }

    public void sell(Product product, Buyer buyer) {
        products.add(SoldProduct.sell(product, buyer));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof SoldProductList))
            return false;

        SoldProductList that = (SoldProductList) o;
        return products.equals(that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [count: " + products.size() + "]";
    }
}
