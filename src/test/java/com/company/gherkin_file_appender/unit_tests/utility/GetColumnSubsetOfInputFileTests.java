package com.company.gherkin_file_appender.unit_tests.utility;

import com.company.gherkin_file_appender.config.Prototype_AppendDataToFeatureFileUtility;
import com.company.gherkin_file_appender.unit_tests.config.TestBase;
import org.junit.Test;

import java.util.List;

public class GetColumnSubsetOfInputFileTests extends TestBase {
    Prototype_AppendDataToFeatureFileUtility utility;

    public GetColumnSubsetOfInputFileTests() {
        utility = new Prototype_AppendDataToFeatureFileUtility();
    }

    @Test
    public void retrieveSingleColumnFromInputFileTest() {
        utility.readAndCleanseInputDataFile("SampleData.csv", 8, 4);
        print(utility.getSpecificColumnFromInputFile2DArray(0));
        print(utility.getSpecificColumnFromInputFile2DArray(1));
    }

    @Test
    public void retrieveColumnRangeFromInputFileTest() {
        utility.readAndCleanseInputDataFile("SampleData.csv", 8, 4);
        // TODO Implement this
        print(utility.getColumnSubsetFromInputFile2DArray(0, 1));
    }
}