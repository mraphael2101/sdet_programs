package com.company.gherkin_file_apender.unit_tests_other;

import com.company.sdet_programs.two_dim_array.predicatesAndStreams.Experiment;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Predicate;

import static java.lang.Double.parseDouble;
import static java.lang.System.out;

public class PredicateFilterTests {
    Experiment experiment;

    String[][] array2d = {
            { "Tesla", "74990.90" },
            { "Ferrari", "99990.90" },
            { "Mercedes", "60000" }
    };

    @Before
    public void beforeTest() {
        experiment = new Experiment();
    }

    @Test
    public void filterResultsUsingPredicate_LessThan() {
        Predicate<String> predicateLessThan = s -> (parseDouble(s) < 100000);
        var resultA = experiment.filterA(array2d, predicateLessThan, 1);
        resultA.forEach(out::println);
    }

    @Test
    public void filterResultsUsingPredicate_GreaterThan() {
        Predicate<String> predicateGreaterThan = s -> (parseDouble(s) > 75000);
        var resultB = experiment.filterB(array2d, predicateGreaterThan, 1);
        resultB.forEach((k, v) -> out.format("%d: %s%n", k, Arrays.toString(v)));
    }

}