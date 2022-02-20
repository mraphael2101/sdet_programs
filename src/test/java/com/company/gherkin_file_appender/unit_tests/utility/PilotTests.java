package com.company.gherkin_file_appender.unit_tests.utility;

import org.junit.Test;
import com.company.gherkin_file_appender.unit_tests.config.TestBase;

import static org.junit.Assert.*;

public class PilotTests extends TestBase {
    @Test
    public void readDataSourceTest() {
        assertEquals("8 rows", 8, rows.size());
    }

    @Test
    public void copyFeatureFileTest() {
        assertTrue(utility2.copyFeatureFile("demo.feature"));
    }

    // *** Happy Path Scenarios ***
    @Test
    public void appendAllSourceDataToFeatureFile() {
        assertTrue(utility2.copyFeatureFile("demo.feature"));
        assertTrue(utility2.appendDataToNewFeatureFile("alldata"));
    }

    @Test
    public void appendRowRangeToFeatureFile_StartFromIndexZero() {
        int[] range = { 0, 3 };
        assertTrue(utility2.copyFeatureFile("demo.feature"));
        assertTrue(utility2.appendDataToNewFeatureFile("rowsrange", range));
    }

    @Test
    public void appendRowRangeToFeatureFile_StartFromIndexOne() {
        int[] range = { 1, 4 };
        assertTrue(utility2.copyFeatureFile("demo.feature"));
        assertTrue(utility2.appendDataToNewFeatureFile("rowsrange", range));
    }

    @Test
    public void appendSingleRowToFeatureFile() {
        int[] index = { 3 };
        assertTrue(utility2.copyFeatureFile("demo.feature"));
        assertTrue(utility2.appendDataToNewFeatureFile("row", index));
    }
    // *** Ends Here ***
}
