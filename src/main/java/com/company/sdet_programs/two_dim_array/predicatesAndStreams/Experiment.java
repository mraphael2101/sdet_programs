package com.company.sdet_programs.two_dim_array.predicatesAndStreams;


import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.lang.Double.parseDouble;
import static java.lang.System.out;
import static java.util.Arrays.copyOf;
import static java.util.stream.Collectors.toList;

public class Experiment {
    public static void main(String... args) {
        String[][] array2d = {
                { "Tesla", "10000.99" },
                { "Ferrari", "200000" },
                { "Mercedes", "60000.99" }
        };
        Predicate<String> pred = s -> (parseDouble(s) > 5);
        var result = filter(array2d, pred, 1);
        result.forEach(out::println);
        //var result2 = filter(array2d, pred, 1);
        //result2.forEach((k, v) -> out.format("%d: %s%n", k, Arrays.toString(v)));
    }

    public static List<ResultSelection> filter(String[][] array, Predicate<String> predicate, int column) {
        return IntStream.range(0, array.length)
                .filter(i -> predicate.test(array[i][column]))
                .mapToObj(i -> new ResultSelection(i, copyOf(array[i], array[i].length)))
                .collect(toList());
    }

    public static Map<Integer, String[]> filter2(String[][] array, Predicate<String> predicate, int column) {
        var result = new TreeMap<Integer, String[]>();
        for(int i = 0; i < array.length; i++) {
            if(predicate.test(array[i][column])) {
                result.put(i, copyOf(array[i], array[i].length));
            }
        }
        return result;
    }
}

