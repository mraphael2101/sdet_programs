package com.company.gherkin_file_apender.unit_tests.utility1;

import org.junit.Test;
import com.company.gherkin_file_apender.unit_tests.config.TestBase_Utility1;

public class GetColumnSubsetOfInputFileTests extends TestBase_Utility1 {
    @Test
    public void retrieveSingleColumnFromInputFileTest() {
        simOnlyPlanUtility.readAndCleanseInputDataFile("SampleData.csv", 6, 2);
        print(simOnlyPlanUtility.getColumnSubsetOfInputFile(2, 2));
    }

    @Test
    public void retrieveColumnRangeFromInputFileTest() {
        simOnlyPlanUtility.readAndCleanseInputDataFile("SampleData.csv", 8, 4);
        print(simOnlyPlanUtility.getColumnSubsetOfInputFile(1, 3));
    }
}