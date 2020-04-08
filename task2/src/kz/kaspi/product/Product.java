package kz.kaspi.product;

import java.util.Objects;

/**
 * Base abstract class for products
 */
public abstract class Product {

    private double price;
    private String title;

    public Product(double price, String title) {
        this.price = price;
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Product))
            return false;

        Product product = (Product) o;
        return product.price == price &&
                title.equals(product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, title);
    }

    @Override
    public String toString() {
        return "title: " + title + ", price: " + price;
    }
}
