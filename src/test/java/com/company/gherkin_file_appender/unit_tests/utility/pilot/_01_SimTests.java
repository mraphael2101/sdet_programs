package com.company.gherkin_file_appender.unit_tests.utility.pilot;

import com.company.gherkin_file_appender.config.Prototype_AppendDataToFeatureFileUtility;
import com.company.gherkin_file_appender.unit_tests.config.TestBase;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class _01_SimTests extends TestBase {
    private Prototype_AppendDataToFeatureFileUtility utility;

    public _01_SimTests() {
        utility = new Prototype_AppendDataToFeatureFileUtility();
    }

    @Test
    public void happyPath_Sim_2DArray() {
        utility.readCleanseDataSourceFileInto2DArray("SampleData.csv");
        int[] range = { 1, 3 };
        assertTrue(utility.copyFeatureFile("demo.feature"));
        assertTrue(utility.appendDataToNewFeatureFile("colsrange", range));
    }

    @Test
    public void happyPath_Sim_ArrayList() {
        utility.readDataSourceFileIntoList("SampleData.csv");
        int[] range = { 2, 7 };
        assertTrue(utility.copyFeatureFile("demo.feature"));
        assertTrue(utility.appendDataToNewFeatureFile("rowsrange", range));
    }
}
