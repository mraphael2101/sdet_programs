package com.company.gherkin_file_appender.unit_tests.utility.capabilities;

import com.company.gherkin_file_appender.config.ResultSelection;
import com.company.gherkin_file_appender.config.Prototype_AppendDataToFeatureFileUtility;
import com.company.gherkin_file_appender.unit_tests.config.TestBase;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.lang.Double.parseDouble;
import static java.lang.System.out;

public class PredicateFilterTests {
    Prototype_AppendDataToFeatureFileUtility utility3;

    String[][] array2d = {
            { "Concorde", "74990.90" },
            { "Boeing 777", "99990.90" },
            { "Private Jet", "60000" }
    };

    @Before
    public void beforeTest() {
        utility3 = new Prototype_AppendDataToFeatureFileUtility();
    }

    @Test
    public void filterResultsUsingPredicate_StringContains() {
        String value = "Concorde";
        Predicate<String> predStringContains = s -> (s.contains(value));
        List<ResultSelection> resultList = utility3.filterRowsByList(array2d, predStringContains, 0);
        resultList.forEach(out::println);
    }

    @Test
    public void filterResultsUsingPredicate_DoubleLessThan() {
        double value = 100000;
        Predicate<String> predDoubleLessThan = s -> (parseDouble(s) < value);
        List<ResultSelection> resultList = utility3.filterRowsByList(array2d, predDoubleLessThan, 1);
        resultList.forEach(out::println);
    }

    @Test
    public void filterResultsUsingPredicate_DoubleGreaterThan() {
        double value = 75000;
        Predicate<String> predDoubleGreaterThan = s -> (parseDouble(s) > value);
        Map<Integer, String[]> resultMap = utility3.filterRowsByMap(array2d, predDoubleGreaterThan, 1);
        resultMap.forEach((k, v) -> out.format("%d: %s%n", k, Arrays.toString(v)));
    }
}