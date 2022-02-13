package com.company.gherkin_file_apender.unit_tests.utility2;

import org.junit.Test;
import com.company.gherkin_file_apender.unit_tests.config.TestBase_Utility2;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

public class PilotTests extends TestBase_Utility2 {
    @Test
    public void readDataSourceTest() {
        assertEquals("8 rows", 8, rows.size());
    }

    @Test
    public void readAndCleanseInputDataTest() {
        print(simOnlyPlanUtility.readAndCleanseInputDataFile("SampleData.csv", 4, 4));
    }

    @Test
    public void copyFeatureFileTest() {
        assertTrue(simOnlyPlanUtility.copyFeatureFile("demo.feature"));
    }

    @Test
    public void appendAllSourceDataToFeatureFile() {
        assertTrue(simOnlyPlanUtility.copyFeatureFile("demo.feature"));
        assertTrue(simOnlyPlanUtility.appendDataToNewFeatureFile("alldata"));
    }

    @Test
    public void appendRowRangeToFeatureFile() {
        int[] range = { 0, 1 };
        assertTrue(simOnlyPlanUtility.copyFeatureFile("demo.feature"));
        assertTrue(simOnlyPlanUtility.appendDataToNewFeatureFile("rowsrange", range));
    }

    @Test
    public void appendSingleRowToFeatureFile() {
        int[] index = { 3 };
        assertTrue(simOnlyPlanUtility.copyFeatureFile("demo.feature"));
        assertTrue(simOnlyPlanUtility.appendDataToNewFeatureFile("row", index));
    }

}
