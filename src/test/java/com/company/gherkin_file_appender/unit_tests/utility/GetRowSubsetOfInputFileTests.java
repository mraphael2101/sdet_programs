package com.company.gherkin_file_appender.unit_tests.utility;

import org.junit.Test;
import com.company.gherkin_file_appender.unit_tests.config.TestBase;

import static org.junit.Assert.assertEquals;

public class GetRowSubsetOfInputFileTests extends TestBase {
    @Test
    public void retrieveSingleRowFromInputFileTest() {
        print(simOnlyPlanUtility.getSpecificRowFromInputFileArrayList(4));
        assertEquals("Row 3 Col 1, Row 3 Col 2, Row 3 Col 3, Row 3 Col 4",
                simOnlyPlanUtility.getSpecificRowFromInputFileArrayList(4));
    }

    @Test
    public void retrieveRowRangeFromInputTest(){
        print(simOnlyPlanUtility.getRowRangeFromInputFileArrayList(1,2));
        assertEquals("[Row 1 Col 1, Row 1 Col 2, Row 1 Col 3, Row 1 Col 4, Row 2 Col 1, Row 2 Col 2, Row 2 Col 3, Row 2 Col 4]",
                simOnlyPlanUtility.getRowRangeFromInputFileArrayList(1,2).toString());
    }
}