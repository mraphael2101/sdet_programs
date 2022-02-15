package com.company.gherkin_file_apender.unit_tests.utility1;

import com.company.gherkin_file_appender.config.ResultSelection;
import com.company.gherkin_file_appender.config._01_Utility_AppendDataToFeatureFile;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.lang.Double.parseDouble;
import static java.lang.System.out;

public class PredicateFilterTests {
    _01_Utility_AppendDataToFeatureFile filtersWithPredsAndStreams;

    String[][] array2d = {
            { "Concorde", "74990.90" },
            { "Boeing 777", "99990.90" },
            { "Private Jet", "60000" }
    };

    @Before
    public void beforeTest() {
        filtersWithPredsAndStreams = new _01_Utility_AppendDataToFeatureFile();
    }

    @Test
    public void filterResultsUsingPredicate_StringContains() {
        String value = "Concorde";
        Predicate<String> predStringContains = s -> (s.contains(value));
        List<ResultSelection> resultList = filtersWithPredsAndStreams.filterReturnsList(array2d, predStringContains, 0);
        resultList.forEach(out::println);
    }

    @Test
    public void filterResultsUsingPredicate_DoubleLessThan() {
        Predicate<String> predDoubleLessThan = s -> (parseDouble(s) < 100000);
        List<ResultSelection> resultList = filtersWithPredsAndStreams.filterReturnsList(array2d, predDoubleLessThan, 1);
        resultList.forEach(out::println);
    }

    @Test
    public void filterResultsUsingPredicate_DoubleGreaterThan() {
        Predicate<String> predDoubleGreaterThan = s -> (parseDouble(s) > 75000);
        Map<Integer, String[]> resultMap = filtersWithPredsAndStreams.filterReturnsMap(array2d, predDoubleGreaterThan, 1);
        resultMap.forEach((k, v) -> out.format("%d: %s%n", k, Arrays.toString(v)));
    }
}