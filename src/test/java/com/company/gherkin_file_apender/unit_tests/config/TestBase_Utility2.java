package com.company.gherkin_file_apender.unit_tests.config;

import com.company.gherkin_file_appender._02_Rewrite_FeatureFile_Utility;
import org.junit.Before;

import java.util.List;

public class TestBase_Utility2 {

    //@InjectMocks
    protected _02_Rewrite_FeatureFile_Utility simOnlyPlanUtility;

    //@Mock
    //Type1 myType1 = new Type1();
    //@Mock
    //Type2 myType2 = new Type2();

    public TestBase_Utility2() {
    }

    @Before
    public void beforeTest() {
        //MockitoAnnotations.openMocks(this);
        simOnlyPlanUtility = new _02_Rewrite_FeatureFile_Utility();
    }

    protected void print(List<String> fileInputRowsList) {
        System.out.println(fileInputRowsList);
    }

    protected void print(String value) {
        System.out.println(value);
    }

}
