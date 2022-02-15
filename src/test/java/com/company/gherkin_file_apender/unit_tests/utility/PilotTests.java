package com.company.gherkin_file_apender.unit_tests.utility;

import org.junit.Test;
import com.company.gherkin_file_apender.unit_tests.config.TestBase;

import static org.junit.Assert.*;

public class PilotTests extends TestBase {
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

    // *** Happy Path Scenarios ***
    @Test
    public void appendAllSourceDataToFeatureFile() {
        assertTrue(simOnlyPlanUtility.copyFeatureFile("demo.feature"));
        assertTrue(simOnlyPlanUtility.appendDataToNewFeatureFile("alldata"));
    }

    @Test
    public void appendRowRangeToFeatureFile_StartFromIndexZero() {
        int[] range = { 0, 3 };
        assertTrue(simOnlyPlanUtility.copyFeatureFile("demo.feature"));
        assertTrue(simOnlyPlanUtility.appendDataToNewFeatureFile("rowsrange", range));
    }

    @Test
    public void appendRowRangeToFeatureFile_StartFromIndexOne() {
        int[] range = { 1, 4 };
        assertTrue(simOnlyPlanUtility.copyFeatureFile("demo.feature"));
        assertTrue(simOnlyPlanUtility.appendDataToNewFeatureFile("rowsrange", range));
    }

    @Test
    public void appendSingleRowToFeatureFile() {
        int[] index = { 3 };
        assertTrue(simOnlyPlanUtility.copyFeatureFile("demo.feature"));
        assertTrue(simOnlyPlanUtility.appendDataToNewFeatureFile("row", index));
    }
    // *** Ends Here ***
}
