package com.company.gherkin_file_appender.unit_tests.utility.pilot;

import com.company.gherkin_file_appender.config.Prototype_AppendDataToFeatureFileUtility;
import com.company.gherkin_file_appender.unit_tests.config.TestBase;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FirstCsvFileTests extends TestBase {
    private Prototype_AppendDataToFeatureFileUtility utility;

    public FirstCsvFileTests() {
        utility = new Prototype_AppendDataToFeatureFileUtility();
    }

    @Test
    public void happyPath_RetrieveColumn() {
        utility.readCleanseDataSourceFileInto2DArray("SampleData.csv");
        assertTrue(utility.copyFeatureFile("demo.feature"));
        assertTrue(utility.appendDataToNewFeatureFile("column", new int[] { 0 }));
    }

    @Test
    public void happyPath_RetrieveColumnRange() {
        utility.readCleanseDataSourceFileInto2DArray("SampleData.csv");
        assertTrue(utility.copyFeatureFile("demo.feature"));
        assertTrue(utility.appendDataToNewFeatureFile("colsrange", new int[] { 1, 3 }));
    }

    @Test
    public void happyPath_RetrieveRowRange() {
        utility.readDataSourceFileIntoList("SampleData.csv");
        assertTrue(utility.copyFeatureFile("demo.feature"));
        assertTrue(utility.appendDataToNewFeatureFile("rowsrange", new int[]  { 2, 7 }));
    }
}
