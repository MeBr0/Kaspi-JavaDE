package kz.kaspi.third;

import kz.kaspi.util.Generator;

import java.util.Objects;

/**
 * Construct arrays of clothes
 * Fill it with clothes
 * Get the most profitable clothes
 *
 * @author A.Yergali
 */
public class Main {

    public static void main(String[] args) {

        Clothes[] array = Generator.getArray();

        double max = 0;
        Clothes target = null;

        for (Clothes clothes: array) {
            if (clothes.getPotentialProfit() > max) {
                max = clothes.getPotentialProfit();
                target = clothes;
            }
        }

        Objects.requireNonNull(target, "Clothes not found");
        System.out.println(String.format("%s %.2f", target.getTitle(), target.getPotentialProfit()));
    }
}
