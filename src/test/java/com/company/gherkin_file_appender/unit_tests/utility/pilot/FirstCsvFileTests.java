package com.company.gherkin_file_appender.unit_tests.utility.pilot;

import com.company.gherkin_file_appender.config.Prototype_AppendDataToFeatureFileUtility;
import com.company.gherkin_file_appender.unit_tests.config.TestBase;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FirstCsvFileTests extends TestBase {
    private Prototype_AppendDataToFeatureFileUtility utility;
    private int[] range;

    public FirstCsvFileTests() {
        utility = new Prototype_AppendDataToFeatureFileUtility();
    }

    @Test
    public void happyPath_RetrieveColumn() {
        utility.readCleanseDataSourceFileInto2DArray("SampleData.csv");
        range = new int[] { 1 };
        assertTrue(utility.copyFeatureFile("demo.feature"));
        assertTrue(utility.appendDataToNewFeatureFile("column", range));
    }

    @Test
    public void happyPath_RetrieveColumnRange() {
        utility.readCleanseDataSourceFileInto2DArray("SampleData.csv");
        range = new int[] { 1, 3 };
        assertTrue(utility.copyFeatureFile("demo.feature"));
        assertTrue(utility.appendDataToNewFeatureFile("colsrange", range));
    }

    @Test
    public void happyPath_RetrieveRowRange() {
        utility.readDataSourceFileIntoList("SampleData.csv");
        range = new int[]  { 2, 7 };
        assertTrue(utility.copyFeatureFile("demo.feature"));
        assertTrue(utility.appendDataToNewFeatureFile("rowsrange", range));
    }
}
