package org.example;

import org.junit.jupiter.api.Test;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;



public class BiggestEvenNumber {

    /* This function takes two positive
numbers x and y and returns the
biggest even integer number that
is in the range [x, y] inclusive.
If there's no such number, then
the function should return -1. */

    public static int biggestEvenGood(int x, int y) {
        int biggestEven = -1;
        for (int i = x; i < y; i++) {
            if (i % 2 == 0 &&  i > biggestEven) {
                biggestEven = i;
            }
        }
        return biggestEven;
    }

    // another implementation of biggestEven
    public static int biggestEvenGood2(int x, int y) {
        // Handle the case where x is greater than y
        if (x > y) {
            return -1;
        }

        // Start the loop from y and iterate downwards to x
        for (int i = y; i >= x; i--) {
            // Check if the current number is even
            if (i % 2 == 0) {
                return i; // Return the biggest even number
            }
        }

        // If no even number is found, return -1
        return -1;
    }

    // are you sure?
    public static int biggestEvenSure(int x, int y) {
        // Handle the case where x is greater than y
        if (x > y) {
            return -1;
        }

        // If x is even and within the range [x, y], it is the biggest even number.
        if (x % 2 == 0) {
            return x;
        }

        // Find the biggest even number by iterating downwards from y
        for (int i = y; i >= x; i--) {
            // Check if the current number is even
            if (i % 2 == 0) {
                return i; // Return the biggest even number
            }
        }

        // If no even number is found, return -1
        return -1;
    }

    // are you sure? bis
    public static int biggestEvenSure2(int x, int y) {
        // Handle the case where x is greater than y
        if (x > y) {
            return -1;
        }

        // Find the biggest even number by iterating upwards from x
        for (int i = x; i <= y; i++) {
            // Check if the current number is even
            if (i % 2 == 0) {
                return i; // Return the biggest even number
            }
        }

        // If no even number is found, return -1
        return -1;
    }

    // GPT4, first try
    public static int biggestEvenGPT4(int x, int y) {
        while (y >= x) {
            if (y % 2 == 0) {
                return y;
            }
            y--;
        }
        return -1;
    }

    public static int biggestEven(int x, int y) {
        // Starting from y and moving down to x
        for (int i = y; i >= x; i--) {
            // Check if the number is even
            if (i % 2 == 0) {
                return i;
            }
        }

        // If no even number is found in the given range
        return -1;
    }





    @Test
    public void testXY() {

        assertEquals(8, (biggestEven(1, 9)));
        assertEquals(-1, (biggestEven(1, 1)));

        assertEquals(-1, (biggestEven(-1, -12)));
        assertEquals(-1, biggestEven(-3, -4));
        assertEquals(-1, biggestEven(-2, -4));
        assertEquals(10, (biggestEven(1, 10)));
        assertEquals(2, (biggestEven(2, 2)));

        assertEquals(10, (biggestEven(8, 10)));

        assertEquals(10, biggestEven(7, 10));
        assertEquals(8, biggestEven(7, 9));
        assertEquals(-1, biggestEven(7, 7));
        assertEquals(2, biggestEven(1, 2));


    }

    private int[] biggestEvenFunctions(int x, int y) {
        return new int[]{
            //    biggestEvenGood(x, y), // actually bad!
                biggestEvenGood2(x, y),
            //    biggestEvenSure(x, y), // actually bad!
            //    biggestEvenSure2(x, y), // actually bad!
                biggestEvenGPT4(x, y),
                biggestEven(x, y)
        };
    }

    private void assertAllEqual(int[] results) {
        for (int i = 1; i < results.length; i++) {
            assertEquals(results[0], results[i]);
        }
    }

    @Test
    public void testDifferentialTestingXY() {
        assertAllEqual(biggestEvenFunctions(1, 9));
        assertAllEqual(biggestEvenFunctions(1, 1));
        assertAllEqual(biggestEvenFunctions(-1, -12));
        assertAllEqual(biggestEvenFunctions(-3, -4));
        assertAllEqual(biggestEvenFunctions(-2, -4));
        assertAllEqual(biggestEvenFunctions(1, 10));
        assertAllEqual(biggestEvenFunctions(2, 2));
        assertAllEqual(biggestEvenFunctions(8, 10));
        assertAllEqual(biggestEvenFunctions(7, 10));
        assertAllEqual(biggestEvenFunctions(7, 9));
        assertAllEqual(biggestEvenFunctions(7, 7));
        assertAllEqual(biggestEvenFunctions(1, 2));
    }


    @ParameterizedTest
    @CsvSource({
            "-1,-12,-1",
            "-3,-4,-1",
            "-2,-4,-1",
            "1,10,10",
            "2,2,2",
            "8,10,10",
            "7,10,10",
            "7,9,8",
            "7,7,-1",
            "1,2,2"
    })
    void testParameterizedXY(int x, int y, int expected) {
        int[] results = biggestEvenFunctions(x, y);
        assertAllEqual(results);
        assertEquals(expected, results[0]);
    }










}