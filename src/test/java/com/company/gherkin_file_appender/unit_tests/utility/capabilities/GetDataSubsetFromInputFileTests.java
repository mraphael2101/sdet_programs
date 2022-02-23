package com.company.gherkin_file_appender.unit_tests.utility.capabilities;

import com.company.gherkin_file_appender.config.Prototype_AppendDataToFeatureFileUtility;
import com.company.gherkin_file_appender.unit_tests.config.TestBase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetDataSubsetFromInputFileTests extends TestBase {

    Prototype_AppendDataToFeatureFileUtility utility1;

    public GetDataSubsetFromInputFileTests() {
        utility1 = new Prototype_AppendDataToFeatureFileUtility();
    }

    @Test
    public void retrieveSingleColumnFromInputFileTest() {
        print(utility1.readCleanseDataSourceFileInto2DArray("SampleData.csv"));

        print(utility1.getSpecificColumnFromInputFile2DArray(0));
        assertEquals("[Column 1, Row 1 Col 1, Row 2 Col 1, Row 3 Col 1, Row 4 Col 1, Row 5 Col 1, Row 6 Col 1]",
                utility1.getSpecificColumnFromInputFile2DArray(0).toString());

        print(utility1.getSpecificColumnFromInputFile2DArray(3));
        assertEquals("[ Column 4,  Row 1 Col 4,  Row 2 Col 4,  Row 3 Col 4,  Row 4 Col 4,  Row 5 Col 4,  Row 6 Col 4]",
                utility1.getSpecificColumnFromInputFile2DArray(3).toString());
    }

    @Test
    public void retrieveColumnRangeFromInputFileTest() {
        print(utility1.readCleanseDataSourceFileInto2DArray("SampleData.csv"));

        print(utility1.getColumnRangeFromInputFile2DArray(1, 3));
        assertEquals("[Column 1,  Column 2,  Column 3, Row 1 Col 1,  Row 1 Col 2,  Row 1 Col 3, Row 2 Col 1,  Row 2 Col 2,  Row 2 Col 3, Row 3 Col 1,  Row 3 Col 2,  Row 3 Col 3, Row 4 Col 1,  Row 4 Col 2,  Row 4 Col 3, Row 5 Col 1,  Row 5 Col 2,  Row 5 Col 3, Row 6 Col 1,  Row 6 Col 2,  Row 6 Col 3]",
                utility1.getColumnRangeFromInputFile2DArray(1,3).toString());

        print(utility1.getColumnRangeFromInputFile2DArray(2, 4));
        assertEquals("[ Column 2,  Column 3,  Column 4,  Row 1 Col 2,  Row 1 Col 3,  Row 1 Col 4,  Row 2 Col 2,  Row 2 Col 3,  Row 2 Col 4,  Row 3 Col 2,  Row 3 Col 3,  Row 3 Col 4,  Row 4 Col 2,  Row 4 Col 3,  Row 4 Col 4,  Row 5 Col 2,  Row 5 Col 3,  Row 5 Col 4,  Row 6 Col 2,  Row 6 Col 3,  Row 6 Col 4]",
                utility1.getColumnRangeFromInputFile2DArray(2,4).toString());
    }

    @Test
    public void retrieveSingleRowFromInputFileTest() {
        utility2.readDataSourceFileIntoList("SampleData.csv");

        print(utility2.getSpecificRowFromInputFileArrayList(4));
        assertEquals("Row 3 Col 1, Row 3 Col 2, Row 3 Col 3, Row 3 Col 4",
                utility2.getSpecificRowFromInputFileArrayList(4));
    }

    @Test
    public void retrieveRowRangeFromInputFileTest(){
        utility2.readDataSourceFileIntoList("SampleData.csv");

        print(utility2.getRowRangeFromInputFileArrayList(1,2));
        assertEquals("[Row 1 Col 1, Row 1 Col 2, Row 1 Col 3, Row 1 Col 4, Row 2 Col 1, Row 2 Col 2, Row 2 Col 3, Row 2 Col 4]",
                utility2.getRowRangeFromInputFileArrayList(1,2).toString());
    }

}