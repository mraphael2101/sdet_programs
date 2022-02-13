package com.company.gherkin_file_apender.unit_tests.utility2;

import org.junit.Test;
import com.company.gherkin_file_apender.unit_tests.config.TestBase_Utility2;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

public class PilotTests extends TestBase_Utility2 {
    @Test
    public void readDataSourceTest() {
        List<String> rows = simOnlyPlanUtility.readDataSourceFileIntoList("SampleData.csv");
        assertEquals("8 rows", 8, rows.size());
    }

    @Test
    public void readAndCleanseInputDataTest() {
        print(simOnlyPlanUtility.readAndCleanseInputDataFile("SampleData.csv", 4, 4));
    }

    @Test
    public void copyFeatureFileTest() {
        simOnlyPlanUtility.readDataSourceFileIntoList("SampleData.csv");
        assertTrue(simOnlyPlanUtility.copyFeatureFile("demo.feature"));
    }

    @Test
    public void happyPathScenario() throws FileNotFoundException {
        simOnlyPlanUtility.readDataSourceFileIntoList("SampleData.csv");
        assertTrue(simOnlyPlanUtility.copyFeatureFile("demo.feature"));
        assertTrue(simOnlyPlanUtility.appendDataToNewFeatureFile());
    }
}
