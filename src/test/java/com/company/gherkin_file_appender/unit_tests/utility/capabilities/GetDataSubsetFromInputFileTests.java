package com.company.gherkin_file_appender.unit_tests.utility.capabilities;

import com.company.gherkin_file_appender.config.Prototype_AppendDataToFeatureFileUtility;
import com.company.gherkin_file_appender.unit_tests.config.TestBase;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GetDataSubsetFromInputFileTests extends TestBase {

    Prototype_AppendDataToFeatureFileUtility utility1;

    public GetDataSubsetFromInputFileTests() {
        utility1 = new Prototype_AppendDataToFeatureFileUtility();
    }

    @Test
    public void retrieveSingleRowFromInputFileTest() {
        utility2.readDataSourceFileIntoList("Sample_Data.csv");

        print(utility2.getSpecificRowFromInputFileArrayList(4));
        assertEquals("Row 3 Col 1, Row 3 Col 2, Row 3 Col 3, Row 3 Col 4",
                utility2.getSpecificRowFromInputFileArrayList(4));
    }

    @Test
    public void retrieveRowRangeFromInputFileTest(){
        utility2.readDataSourceFileIntoList("Sample_Data.csv");

        print(utility2.getRowRangeFromInputFileArrayList(1,2));
        assertEquals("[Row 1 Col 1, Row 1 Col 2, Row 1 Col 3, Row 1 Col 4, Row 2 Col 1, Row 2 Col 2, Row 2 Col 3, Row 2 Col 4]",
                utility2.getRowRangeFromInputFileArrayList(1,2).toString());
    }

    @Test
    public void retrieveSingleColumnFromInputFileTest_WithCleansing() {
        print(utility1.readCleanseDataSourceFileInto2DArray("Sample_Data.csv", false));

        print(utility1.getSpecificColumnFromInputFile2DArray(0));
        assertEquals("[Column 1, Row 1 Col 1, Row 2 Col 1, Row 3 Col 1, Row 4 Col 1, Row 5 Col 1, Row 6 Col 1, Row 7 Col 1]",
                utility1.getSpecificColumnFromInputFile2DArray(0).toString());

        print(utility1.getSpecificColumnFromInputFile2DArray(3));
        assertEquals("[ Column 4,  Row 1 Col 4,  Row 2 Col 4,  Row 3 Col 4,  Row 4 Col 4,  Row 5 Col 4,  Row 6 Col 4,  Row 7 Col 4]",
                utility1.getSpecificColumnFromInputFile2DArray(3).toString());
    }

    @Test
    public void retrieveColumnRangeFromInputFileTest_WithCleansing() {
        utility1.readCleanseDataSourceFileInto2DArray("Sample_Data.csv", false);

        String[][] expected2DArr = initExpected2DArr(8,4);
        print(expected2DArr);

        String[][] actual2DArr = utility1.getColumnRangeFromInputFile2DArray(0,3);
        print(actual2DArr);

        assertThat(actual2DArr).isNotSameAs(expected2DArr);  // if both object refs are the same then something is wrong

        assertThat(expected2DArr[0][0]).isEqualTo("Column 1");
        assertThat(actual2DArr[0][0]).isEqualTo("Column 1");

        assertThat(Arrays.deepEquals(expected2DArr, actual2DArr)).isTrue();
    }

}