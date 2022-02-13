package com.company.gherkin_file_apender.unit_tests.config;

import com.company.gherkin_file_appender._01_Utility_AppendDataToFeatureFile;
import org.junit.Before;

import java.util.List;

public class TestBase_Utility1 {

    protected _01_Utility_AppendDataToFeatureFile simOnlyPlanUtility;

    @Before
    public void beforeTest() {
        this.simOnlyPlanUtility = new _01_Utility_AppendDataToFeatureFile();
    }

    protected void print(List<String> fileInputRowsList) {
        System.out.println(fileInputRowsList);
    }

}
