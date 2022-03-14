package com.company.gherkin_file_appender.unit_tests.utility.pilot;

import com.company.gherkin_file_appender.config.Prototype_AppendDataToFeatureFileUtility;
import com.company.gherkin_file_appender.unit_tests.config.TestBase;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class _01_Sample_Data_Tab_Tests extends TestBase {
    private Prototype_AppendDataToFeatureFileUtility utility;

    public _01_Sample_Data_Tab_Tests() {
        utility = new Prototype_AppendDataToFeatureFileUtility();
    }

    @Test
    public void happyPath_RetrieveColumn_WithCleansing() {
        utility.setExcelTab("Sample_Data_Tab");
        utility.readCleanseDataSourceFileInto2DArray("Sample_Data.csv", true);
        int[] range = { 0 };
        assertTrue(utility.copyFeatureFile("demo.feature"));
        assertTrue(utility.appendDataToNewFeatureFile("column", range));
    }

    @Test
    public void happyPath_RetrieveColumnRange_WithCleansing() {
        utility.setExcelTab("Sample_Data_Tab");
        utility.readCleanseDataSourceFileInto2DArray("Sample_Data.csv", true);
        int[] range = { 1, 12 };
        assertTrue(utility.copyFeatureFile("demo.feature"));
        assertTrue(utility.appendDataToNewFeatureFile("colrange", range));
    }

    @Test
    public void happyPath_RetrieveRow_WithCleansing() {
        utility.setExcelTab("Sample_Data_Tab");
        utility.readCleanseDataSourceFileInto2DArray("Sample_Data.csv", true);
        int[] range = { 2 };
        assertTrue(utility.copyFeatureFile("demo.feature"));
        assertTrue(utility.appendDataToNewFeatureFile("row", range));
    }

    @Test
    public void happyPath_RetrieveRowRange_WithCleansing() {
        utility.setExcelTab("Sample_Data_Tab");
        //utility.cleanseColumn("MarksColumn", "2021", "");

        utility.readCleanseDataSourceFileInto2DArray("Sample_Data.csv", true);
        int[] range = { 2, 7 };
        assertTrue(utility.copyFeatureFile("demo.feature"));
        assertTrue(utility.appendDataToNewFeatureFile("rowrange", range));
    }
}
