package com.company.gherkin_file_apender.unit_tests.utility1;

import org.junit.Test;
import com.company.gherkin_file_apender.unit_tests.config.TestBase_Utility1;

public class GetRowSubsetOfInputFileTests extends TestBase_Utility1 {
    @Test
    public void retrieveSingleRowFromInputFileTest() {
        simOnlyPlanUtility.readAndCleanseInputDataFile("SampleData.csv", 8, 4);
        print(simOnlyPlanUtility.getRowSubsetOfInputFile(1));
    }

    @Test
    public void retrieveRowRangeFromInputFileTest() {
        simOnlyPlanUtility.readAndCleanseInputDataFile("SampleData.csv", 8, 4);
        print(simOnlyPlanUtility.getRowSubsetOfInputFile(3));
    }
}
