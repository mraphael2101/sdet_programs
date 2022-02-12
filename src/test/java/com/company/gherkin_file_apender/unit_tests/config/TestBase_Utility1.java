package com.company.gherkin_file_apender.unit_tests.config;

import com.company.gherkin_file_appender._01_Rewrite_FeatureFile_Utility;
import org.junit.Before;

import java.util.List;

public class TestBase_Utility1 {

    protected _01_Rewrite_FeatureFile_Utility simOnlyPlanUtility;

    @Before
    public void beforeTest() {
        this.simOnlyPlanUtility = new _01_Rewrite_FeatureFile_Utility();
    }

    protected void print(List<String> fileInputRowsList) {
        System.out.println(fileInputRowsList);
    }

}
