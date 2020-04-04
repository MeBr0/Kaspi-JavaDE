package kz.kaspi.second;

import kz.kaspi.util.Generator;

import java.util.Arrays;

/**
 * Construct arrays of integers
 * Fill it with random numbers
 * Extend array twice by size
 * Copy second half from first half
 *
 * @author A.Yergali
 */
public class Main {

    public static void main(String[] args) {

        int size = Integer.parseInt(args[0]);
        int[] integers = Generator.getArray(size);
        int[] array = Arrays.copyOf(integers, size * 2);

        System.arraycopy(array, 0, array, size, size);

        Arrays.stream(array).
                mapToObj(i -> i + " ").
                forEach(System.out::print);
    }
}
