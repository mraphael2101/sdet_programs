package com.company.sdet_programs.two_dim_array.predicatesAndStreams;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.lang.Double.parseDouble;
import static java.lang.System.out;
import static java.util.Arrays.copyOf;
import static java.util.stream.Collectors.toList;

public class Experiment {
    public List<ResultSelection> filterA(String[][] array, Predicate<String> predicate, int column) {
        return IntStream.range(0, array.length)
                .filter(i -> predicate.test(array[i][column]))
                .mapToObj(i -> new ResultSelection(i, copyOf(array[i], array[i].length)))
                .collect(toList());
    }

    public Map<Integer, String[]> filterB(String[][] array, Predicate<String> predicate, int column) {
        var result = new TreeMap<Integer, String[]>();
        for(int i = 0; i < array.length; i++) {
            if(predicate.test(array[i][column])) {
                result.put(i, copyOf(array[i], array[i].length));
            }
        }
        return result;
    }
}

