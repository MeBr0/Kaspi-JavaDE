package kz.kaspi.shop.cart;

import kz.kaspi.shop.product.Product;
import kz.kaspi.shop.product.food.Food;
import kz.kaspi.shop.product.type.Expirable;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Collection class for {@link Product} stored in {@link SubCart} classes
 *
 * Have {@link #add(Product)} to add product into basket
 * and {@link #getTotalCost()} for calculating total cost of products
 * and {@link #getNotExpiredProducts()} for getting products which is {@link Expirable} but not expired
 * and {@link #count()} for getting count of products
 * and {@link #clear()} for clearing all products in {@link Cart}
 *
 * @author A.Yergali
 * @version 3.0
 */
public class Cart implements Serializable {

    private SubCart<Food> food;
    private SubCart<Product> nonFood;

    private Cart() {

    }

    {
        food = new SubCart<>();
        nonFood = new SubCart<>();
    }

    public static Cart getInstance() {
        return new Cart();
    }

    /**
     * Sort and add {@link Product} to appropriate {@link SubCart}
     */
    public void add(Product product) {
        if (product instanceof Food)
            food.add((Food) product);

        else
            nonFood.add(product);
    }

    /**
     * Get total cost of all products in basket
     */
    public double getTotalCost() {
        return getFoodCost() + getNonFoodCost();
    }

    /**
     * Get total cost of food products
     */
    public double getFoodCost() {
        return food.getTotalCost();
    }

    /**
     * Get total cost of non food products
     */
    public double getNonFoodCost() {
        return nonFood.getTotalCost();
    }

    /**
     * Return only products that have expiration date
     * and expire date did not passed
     */
    public List<Product> getNotExpiredProducts() {
        List<Product> result = nonFood.getNotExpiredProducts();
        result.addAll(food.getNotExpiredProducts());

        return result;
    }

    /**
     * Return only food products that have expiration date
     * and expire date did not passed
     */
    public List<Food> getFoodNotExpiredProducts() {
        return food.getNotExpiredProducts();
    }

    /**
     * Return only non food products that have expiration date
     * and expire date did not passed
     */
    public List<Product> getNonFoodNotExpiredProducts() {
        return nonFood.getNotExpiredProducts();
    }

    /**
     * Clear basket from all products
     */
    public void clear() {
        food.clear();
        nonFood.clear();
    }

    /**
     * Return count of products
     */
    public int count() {
        return food.count() + nonFood.count();
    }

    public List<Product> getProducts() {
        List<Product> result = nonFood.getProducts();
        result.addAll(food.getProducts());

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Cart))
            return false;

        Cart cart = (Cart) o;
        return food.equals(cart.food) &&
                nonFood.equals(cart.nonFood);
    }

    @Override
    public int hashCode() {
        return Objects.hash(food, nonFood);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [product count: " + count() + "]";
    }
}
