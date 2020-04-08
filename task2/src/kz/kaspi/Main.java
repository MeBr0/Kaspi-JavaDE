package kz.kaspi;

import kz.kaspi.product.Basket;
import kz.kaspi.product.Product;
import kz.kaspi.product.clothes.Clothes.Color;
import kz.kaspi.product.clothes.Clothes.Size;
import kz.kaspi.product.clothes.Jeans;
import kz.kaspi.product.clothes.Jeans.Cloth;
import kz.kaspi.product.clothes.Jeans.Fit;
import kz.kaspi.product.clothes.Shirt;
import kz.kaspi.product.clothes.Shirt.NeckType;
import kz.kaspi.product.food.Kefir;
import kz.kaspi.product.food.Sausage;
import kz.kaspi.product.food.Sausage.MeatType;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Product jeans = new Jeans(2.3, "Jeans, yes it is!", Size.S, Color.BLACK, Fit.SLIM, Cloth.JEAN);
        Product shirt = new Shirt(40, "$40 shirts", Size.L, Color.GREEN, NeckType.HENLEY);
        Product kefir = new Kefir(99.1, "For sure kefir", LocalDate.now().plusDays(20), 20);
        Product anotherKefir = new Kefir(88.9, "Another kefir yeah", LocalDate.now(), 19);
        Product sausage = new Sausage(45.1, "Sausage", LocalDate.now().plusDays(41),
                MeatType.HALF_SMOKED);

        Basket basket = new Basket();

        basket.add(jeans);
        basket.add(shirt);
        basket.add(kefir);
        basket.add(anotherKefir);
        basket.add(sausage);

        System.out.println(basket.getTotalCost());
    }
}
