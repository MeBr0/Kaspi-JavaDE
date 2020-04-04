package kz.kaspi.util;

import kz.kaspi.third.Clothes;
import kz.kaspi.third.Clothes.Color;
import kz.kaspi.third.Clothes.Size;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Generate array of integers with random numbers
 * Generate array of clothes
 */
public class Generator {

    public static int[] getArray(int n) {
        int[] integers = new int[n];
        int range = 100;

        for (int i = 0; i < integers.length; ++i) {
            Random random = new SecureRandom();

            integers[i] = random.nextInt(range);
        }

        return integers;
    }

    public static Clothes[] getArray() {
        Clothes[] array = new Clothes[10];

        array[0] = new Clothes("qwe", Size.L, 123, Color.YELLOW, 51, 812);
        array[1] = new Clothes("dasd", Size.M, 1999.99, Color.BLUE, 1500, 89);
        array[2] = new Clothes("qwreqw", Size.XS, 413.1, Color.RED, 300, 67);
        array[3] = new Clothes("asdz", Size.M, 150, Color.WHITE, 100, 5);
        array[4] = new Clothes("qweqwasda", Size.S, 511.99, Color.BLACK, 500, 99);
        array[5] = new Clothes("ac", Size.L, 112.2, Color.GREEN, 99, 124);
        array[6] = new Clothes("qqqqq", Size.XS, 1.1, Color.GREY, 0.4, 55);
        array[7] = new Clothes("asdasd", Size.XL, 12, Color.RED, 5.9, 14);
        array[8] = new Clothes("qwasasd", Size.XL, 488.88, Color.YELLOW, 400, 65);
        array[9] = new Clothes("q", Size.XS, 10.1, Color.BLUE, 5.1, 122);

        return array;
    }
}
