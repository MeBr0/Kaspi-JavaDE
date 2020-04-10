package kz.kaspi;

import kz.kaspi.shop.Basket;
import kz.kaspi.shop.Cashbox;
import kz.kaspi.shop.product.clothes.Clothes.Color;
import kz.kaspi.shop.product.clothes.Clothes.Size;
import kz.kaspi.shop.product.clothes.Jeans;
import kz.kaspi.shop.product.clothes.Jeans.Cloth;
import kz.kaspi.shop.product.clothes.Jeans.Fit;
import kz.kaspi.shop.product.clothes.Shirt;
import kz.kaspi.shop.product.clothes.Shirt.NeckType;
import kz.kaspi.shop.product.food.Kefir;
import kz.kaspi.shop.product.food.Sausage.Builder;
import kz.kaspi.shop.product.food.Sausage.MeatType;
import kz.kaspi.shop.product.other.CleaningAgent;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();

        Basket basket = Basket.getInstance();
        Cashbox cashbox = new Cashbox();

        basket.add(new Jeans(2.3, "Jeans, yes it is!", now, Size.S, Color.BLACK, Fit.SLIM, Cloth.JEAN));
        basket.add(new Shirt(40, "$40 shirts", now, Size.L, Color.GREEN, NeckType.HENLEY));
        basket.add(new Kefir(99.1, "For sure kefir", now, 20, 20));
        basket.add(new Kefir(88.9, "Another kefir yeah", now,  0, 19));
        basket.add(Builder.getInstance().
                price(45.1).title("Sausage").issueDate(now.minusDays(38)).
                expireDays(41).meatType(MeatType.HALF_SMOKED).
                build());
        basket.add(Builder.getInstance().
                price(999).title("Built sausage").issueDate(now.minusDays(39)).
                expireDays(2).meatType(MeatType.COOKED).
                build());
        basket.add(new CleaningAgent(9999, "007", now.minusYears(1)));

        System.out.println("Total cost is " + cashbox.calculate(basket));
        System.out.println();

        System.out.println("Not expired products in basket:");
        basket.getNotExpiredProducts().forEach(System.out::println);

        basket.clear();
    }
}
