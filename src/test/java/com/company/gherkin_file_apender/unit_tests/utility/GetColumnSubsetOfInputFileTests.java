package com.company.gherkin_file_apender.unit_tests.utility;

import com.company.gherkin_file_apender.unit_tests.config.TestBase;
import org.junit.Test;

public class GetColumnSubsetOfInputFileTests extends TestBase {
    @Test
    public void retrieveSingleColumnFromInputFileTest() {
        simOnlyPlanUtility.readAndCleanseInputDataFile("SampleData.csv", 8, 4);
        print(simOnlyPlanUtility.getColumnSubsetFromInputFileArrayList(2, 2));
    }

    @Test
    public void retrieveColumnRangeFromInputFileTest() {
        simOnlyPlanUtility.readAndCleanseInputDataFile("SampleData.csv", 8, 4);
        print(simOnlyPlanUtility.getColumnSubsetFromInputFileArrayList(1, 3));
    }
}