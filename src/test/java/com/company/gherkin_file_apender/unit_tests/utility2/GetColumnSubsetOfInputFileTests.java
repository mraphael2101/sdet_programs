package com.company.gherkin_file_apender.unit_tests.utility2;

import com.company.gherkin_file_apender.unit_tests.config.TestBase_Utility2;
import org.junit.Test;

public class GetColumnSubsetOfInputFileTests extends TestBase_Utility2 {
    @Test
    public void retrieveSingleColumnFromInputFileTest() {
        //TODO make this work for a range
        //simOnlyPlanUtility.readAndCleanseInputDataFile("SampleData.csv", 8, 4);
        print(simOnlyPlanUtility.readFileIntoList("SampleData.csv"));      // Deprecated
        //print(simOnlyPlanUtility.getColumnSubsetOfInputFile(2, 2));
    }

    @Test
    public void retrieveColumnRangeFromInputFileTest() {
        //TODO make this work for a range
        //simOnlyPlanUtility.readAndCleanseInputDataFile("SampleData.csv", 8, 4);
        print(simOnlyPlanUtility.readFileIntoList("SampleData.csv"));      // Deprecated
        //print(simOnlyPlanUtility.getColumnSubsetOfInputFile(1, 3));
    }
}