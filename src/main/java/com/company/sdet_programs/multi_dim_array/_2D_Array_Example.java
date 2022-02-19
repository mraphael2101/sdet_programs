package com.company.sdet_programs.multi_dim_array;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class _2D_Array_Example {

    static String[][] arr = {
            { "row 1 col 1", "row 1 col 2", "row 1 col 3" },
            { "row 2 col 1", "row 2 col 2", "row 2 col 3" },
            { "row 3 col 1", "row 3 col 2", "row 3 col 3" }
    };

    public static void main(String[] args) {
        var obj = Arrays.stream(arr)
                .flatMap(x -> Arrays.stream(x)
                .filter(y -> y.contains("row 1")))
                .collect(toList());

        _2D_Array_Example objRef = new _2D_Array_Example();
        System.out.println(objRef);
    }

    @Override
    public String toString() {
        return String.format("%s", Arrays.stream(arr).findFirst().get());
    }
}
