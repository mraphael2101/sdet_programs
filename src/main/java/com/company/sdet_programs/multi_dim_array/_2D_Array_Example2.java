package com.company.sdet_programs.multi_dim_array;

import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;

public class _2D_Array_Example2 {

    static String[][] array2d = {
            { "row 1 col 1", "row 1 col 2", "row 1 col 3" },
            { "row 2 col 1", "row 2 col 2", "row 2 col 3" },
            { "row 3 col 1", "row 3 col 2", "row 3 col 3" }
    };

    public static void main(String[] args) {
        // Map the two dimensional array with indices.
        final IntStream intStream = range(0, array2d.length).flatMap(row -> range(0, array2d[row].length).map(col -> {
            final String element = array2d[row][col];
            // E.g. multiply elements in odd numbered rows and columns by two.
            return element;
        }));
        // Prints "1 4 3 8 10 12 7 16 9 ".
        intStream.forEachOrdered(n -> System.out.print(n + " "));
    }
}
