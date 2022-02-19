package com.company.sdet_programs.multi_dim_array;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;

public class _2D_Array_Example2 {

    static String[][] arr2d = {
            { "row 1 col 1", "row 1 col 2", "row 1 col 3" },
            { "row 2 col 1", "row 2 col 2", "row 2 col 3" },
            { "row 3 col 1", "row 3 col 2", "row 3 col 3" }
    };
    static List<String> listSubsetArr = new ArrayList<>();

    public static void main(String[] args) {
        // getColumnSubset(0, 1);
        getColumnSubset(0, 2);
        // getColumnSubset(0, 3);
    }

    public static void getColumnSubset(int startIndex, int endIndex) {
        // Map the two dimensional array with indices
        var intStream = range(0, arr2d.length).flatMap(row -> range(startIndex, endIndex).map(col -> {
            listSubsetArr.add(arr2d[row][col]);
            return row;
        }));
        intStream.forEach(row -> {});

        System.out.println(listSubsetArr);
    }



    /*
        static int[][] arr2d = { { 1, 2, 3 },
                             { 4, 5, 6 },
                             { 7, 8, 9 },
    };
    public static void main(String[] args) {
        // Map the two dimensional array with indices.
        var intStream = range(0, arr2d.length).flatMap(row -> range(0, arr2d[row].length).map(col -> {
            final int element = arr2d[row][col];
            // E.g. multiply elements in odd numbered rows and columns by two.
            return row % 2 == 1 || col % 2 == 1 ? element * 2 : element;
        }));
        // Prints "1 4 3 8 10 12 7 16 9 ".
        intStream.forEachOrdered(n -> System.out.print(n + " "));
    }
     */
}
