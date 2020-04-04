package kz.kaspi.first;

import kz.kaspi.util.Generator;

import java.util.Arrays;

/**
 * Construct arrays of integers
 * Fill it with random numbers
 * Print even of them
 * Print sum of all numbers
 *
 * @author A.Yergali
 */
public class Main {

    public static void main(String[] args) {

        int size = Integer.parseInt(args[0]);
	    int[] integers = Generator.getArray(size);
	    int sum = 0;

        System.out.print("Evens: ");

	    for (int integer: integers) {
	        if (integer % 2 == 0) {
                System.out.print(integer + " ");
            }

	        sum += integer;
        }

	    System.out.println("\nSum: " + sum);

        Arrays.stream(integers).
                mapToObj(i -> i + " ").
                forEach(System.out::print);
    }
}
