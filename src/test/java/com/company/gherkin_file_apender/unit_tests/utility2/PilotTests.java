package com.company.gherkin_file_apender.unit_tests.utility2;

import org.junit.Test;
import com.company.gherkin_file_apender.unit_tests.config.TestBase_Utility2;

import static org.junit.Assert.assertTrue;

public class PilotTests extends TestBase_Utility2 {
    @Test
    public void readAndCleanseInputDataTest() {
        print(simOnlyPlanUtility.readAndCleanseInputDataFile("SampleData.csv", 4, 4));
    }

    @Test
    public void createRevisedOutputGherkinFileTest() {
        simOnlyPlanUtility.createRevisedOutputGherkinFile("SampleData.csv", "demo2.feature");
    }

    @Test
    public void happyPathScenario() {
        assertTrue(simOnlyPlanUtility.createRevisedOutputGherkinFile("SampleData.csv", "demo2.feature"));
    }
}
