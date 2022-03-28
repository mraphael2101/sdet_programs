package com.company.gherkin_file_appender.unit_tests.utility.capabilities;

import org.junit.Test;
import com.company.gherkin_file_appender.unit_tests.config.TestBase;

import java.util.function.Predicate;

import static org.junit.Assert.*;

public class _01_Sanity_Tests extends TestBase {
    @Test
    public void readDataSourceTest() {
        assertEquals("8 rows", 8, rows.length);
    }

    @Test
    public void copyFeatureFileTest() {
        assertTrue(caps_utility.copyFeatureFile("Demo.feature"));
    }

    @Test
    public void appendSingleRowToFeatureFile() {
        Object[] index = { 3 };
        assertTrue(caps_utility.copyFeatureFile("Demo.feature"));
        assertTrue(caps_utility.appendDataToNewFeatureFile("row", index));
    }

    @Test
    public void appendRowRangeToFeatureFile() {
        Object[] range = { 0, 3 };
        assertTrue(caps_utility.copyFeatureFile("Demo.feature"));
        assertTrue(caps_utility.appendDataToNewFeatureFile("rowrange", range));
    }

    @Test
    public void appendRowAndColumnSubsetToFeatureFile_FilteredList() {
        caps_utility.setExcelTab("Sample_Data_Tab");
        caps_utility.readCleanseDataSourceFileInto2DArray("Sample_Data.csv", false);
        // a) Apply function query based on col index to return all rows that match the specified value
        // b) Return ALL results as type List<ResultSelection> with no additional filters
        // Try changing Col with Column below to see the difference in the output
        Predicate<String> predStringEquals = s -> (s.contains("Col"));
        Object[] args = { predStringEquals, 0 };
        assertTrue(caps_utility.copyFeatureFile("Demo.feature"));
        assertTrue(caps_utility.appendDataToNewFeatureFile("filtered_list", args));
    }

    @Test
    public void appendRowAndColumnSubsetToFeatureFile_FilteredMapByColrange() {
        caps_utility.setExcelTab("Sample_Data_Tab");
        caps_utility.readCleanseDataSourceFileInto2DArray("Sample_Data.csv", false);
        // a) Apply function query based on col index to return all rows that match the specified value
        // b) Filter all rows based on the column range
        // Try changing Col with Column below to see the difference in the output
        Predicate<String> predStringEquals = s -> (s.contains("Col"));
        Object[] args = { predStringEquals, 0, 0, 2 };
        assertTrue(caps_utility.copyFeatureFile("Demo.feature"));
        assertTrue(caps_utility.appendDataToNewFeatureFile("filtered_map_by_colrange", args));
    }

    @Test
    public void appendAllSourceDataToFeatureFile() {
        assertTrue(caps_utility.copyFeatureFile("Demo.feature"));
        assertTrue(caps_utility.appendDataToNewFeatureFile("alldata"));
    }

}
