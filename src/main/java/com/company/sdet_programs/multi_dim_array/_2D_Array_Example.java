package com.company.sdet_programs.multi_dim_array;

import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;

public class _2D_Array_Example {

    static String[][] array2d = {
            { "row 1 col 1", "row 1 col 2", "row 1 col 3" },
            { "row 2 col 1", "row 2 col 2", "row 2 col 3" },
            { "row 3 col 1", "row 3 col 2", "row 3 col 3" }
    };

    static int[][] arr = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

    public static void main(String[] args) {
        // Map the two dimensional array with indices.
        final IntStream intStream = range(0, arr.length).flatMap(row -> range(0, arr[row].length).map(col -> {
            final int element = arr[row][col];
            // E.g. multiply elements in odd numbered rows and columns by two.
            return row % 2 == 1 || col % 2 == 1 ? element * 2 : element;
        }));
        // Prints "1 4 3 8 10 12 7 16 9 ".
        intStream.forEachOrdered(n -> System.out.print(n + " "));
    }
}
