package kz.kaspi.shop.product.sold;

import kz.kaspi.person.Buyer;
import kz.kaspi.shop.product.Product;
import kz.kaspi.shop.product.type.Expirable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Immutable class for sold {@link Product}s
 *
 * {@link #expireDate} is null, if {@link Product} is not {@link Expirable}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class SoldProduct implements Serializable {

    private final String title;
    private final double price;
    private final LocalDate expireDate;
    private final LocalDate soldDate;
    private final String buyerId;

    {
        soldDate = LocalDate.now();
    }

    private SoldProduct(Product product, Buyer buyer) {
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.buyerId = buyer.getId();

        if (product instanceof Expirable) {
            Expirable expirable = (Expirable) product;

            this.expireDate = expirable.getExpireDate();
        }
        else {
            this.expireDate = null;
        }
    }

    static SoldProduct sell(Product product, Buyer buyer) {
        return new SoldProduct(product, buyer);
    }

    public boolean isExpirable() {
        return expireDate == null;
    }

    public boolean willExpireWithin(int days) {
        return expireDate.isAfter(LocalDate.now()) &&
                expireDate.isBefore(LocalDate.now().plusDays(days));
    }

    public boolean soldLastDays(int days) {
        return soldDate.isAfter(LocalDate.now().minusDays(days)) &&
                soldDate.isBefore(LocalDate.now().plusDays(1));
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public LocalDate getSoldDate() {
        return soldDate;
    }

    public String getBuyerId() {
        return buyerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof SoldProduct))
            return false;

        SoldProduct that = (SoldProduct) o;
        return Double.compare(that.price, price) == 0 &&
                title.equals(that.title) &&
                Objects.equals(expireDate, that.expireDate) &&
                soldDate.equals(that.soldDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, expireDate, soldDate);
    }

    @Override
    public String toString() {
        String expireDate = this.expireDate == null ? "----" : this.expireDate.toString();

        return getClass().getSimpleName() + " [title: " + title + ", price: " + price + ", buyer id: " + buyerId +
                ", expire date: " + expireDate  + ", sold date: " + soldDate.toString() + "]";
    }
}
