package com.company.gherkin_file_appender.unit_tests.utility;

import com.company.gherkin_file_appender.config.Prototype_AppendDataToFeatureFileUtility;
import com.company.gherkin_file_appender.unit_tests.config.TestBase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetDataSubsetFromInputFileTests extends TestBase {
    Prototype_AppendDataToFeatureFileUtility utility;

    public GetDataSubsetFromInputFileTests() {
        utility = new Prototype_AppendDataToFeatureFileUtility();
    }

    @Test
    public void retrieveSingleColumnFromInputFileTest() {
        utility.readAndCleanseInputDataFile("SampleData.csv", 8, 4);

        print(utility.getSpecificColumnFromInputFile2DArray(0));
        assertEquals("[Row 1 Col 1, Row 2 Col 1, Row 3 Col 1, Row 4 Col 1, Row 5 Col 1, Row 6 Col 1, Row 7 Col 1]",
                utility.getSpecificColumnFromInputFile2DArray(0).toString());

        print(utility.getSpecificColumnFromInputFile2DArray(3));
        assertEquals("[ Row 1 Col 4,  Row 2 Col 4,  Row 3 Col 4,  Row 4 Col 4,  Row 5 Col 4,  Row 6 Col 4,  Row 7 Col 4]",
                utility.getSpecificColumnFromInputFile2DArray(3).toString());
    }

    @Test
    public void retrieveColumnRangeFromInputFileTest() {
        utility.readAndCleanseInputDataFile("SampleData.csv", 8, 4);

        print(utility.getColumnRangeFromInputFile2DArray(0, 2));
        assertEquals("[Row 1 Col 1,  Row 1 Col 2, Row 2 Col 1,  Row 2 Col 2, Row 3 Col 1,  Row 3 Col 2, Row 4 Col 1,  Row 4 Col 2, Row 5 Col 1,  Row 5 Col 2, Row 6 Col 1,  Row 6 Col 2, Row 7 Col 1,  Row 7 Col 2]",
                utility.getColumnRangeFromInputFile2DArray(0,2).toString());

        print(utility.getColumnRangeFromInputFile2DArray(1, 3));
        assertEquals("[ Row 1 Col 2,  Row 1 Col 3,  Row 2 Col 2,  Row 2 Col 3,  Row 3 Col 2,  Row 3 Col 3,  Row 4 Col 2,  Row 4 Col 3,  Row 5 Col 2,  Row 5 Col 3,  Row 6 Col 2,  Row 6 Col 3,  Row 7 Col 2,  Row 7 Col 3]",
                utility.getColumnRangeFromInputFile2DArray(1,3).toString());
    }

    @Test
    public void retrieveSingleRowFromInputFileTest() {
        print(simOnlyPlanUtility.getSpecificRowFromInputFileArrayList(4));
        assertEquals("Row 3 Col 1, Row 3 Col 2, Row 3 Col 3, Row 3 Col 4",
                simOnlyPlanUtility.getSpecificRowFromInputFileArrayList(4));
    }

    @Test
    public void retrieveRowRangeFromInputFileTest(){
        print(simOnlyPlanUtility.getRowRangeFromInputFileArrayList(1,2));
        assertEquals("[Row 1 Col 1, Row 1 Col 2, Row 1 Col 3, Row 1 Col 4, Row 2 Col 1, Row 2 Col 2, Row 2 Col 3, Row 2 Col 4]",
                simOnlyPlanUtility.getRowRangeFromInputFileArrayList(1,2).toString());
    }
}