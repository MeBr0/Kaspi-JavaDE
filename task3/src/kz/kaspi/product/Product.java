package kz.kaspi.product;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Base abstract class for products
 *
 * @author A.Yergali
 * @version 2.0
 */
public abstract class Product {

    private double price;
    private String title;
    private LocalDate issueDate;

    public Product(double price, String title, LocalDate issueDate) {
        this.price = price;
        this.title = title;
        this.issueDate = issueDate;
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

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Product))
            return false;

        Product product = (Product) o;
        return product.price ==  price &&
                title.equals(product.title) &&
                issueDate.equals(product.issueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, title, issueDate);
    }

    @Override
    public String toString() {
        return "title: " + title + ", price: " + price + ", issue date: " + issueDate.toString();
    }
}
