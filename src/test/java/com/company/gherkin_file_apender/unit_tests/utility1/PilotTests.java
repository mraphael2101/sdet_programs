package com.company.gherkin_file_apender.unit_tests.utility1;

import org.junit.Test;
import com.company.gherkin_file_apender.unit_tests.config.TestBase_Utility1;

import java.util.List;

public class PilotTests extends TestBase_Utility1 {
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
        simOnlyPlanUtility.createRevisedOutputGherkinFile("SampleData.csv", "demo2.feature");
    }
}
