package com.company.gherkin_file_apender.unit_tests.config;

import com.company.gherkin_file_appender.config.Utility_AppendDataToFeatureFile;
import org.junit.Before;

import java.util.List;

public class TestBase_Utility {

    //@InjectMocks
    protected Utility_AppendDataToFeatureFile simOnlyPlanUtility;

    protected List<String> rows;
    private String fileName;

    //@Mock
    //Type1 myType1 = new Type1();
    //@Mock
    //Type2 myType2 = new Type2();

    public TestBase_Utility() {
        setFileName("SampleData.csv");
    }

    @Before
    public void beforeTest() {
        //MockitoAnnotations.openMocks(this);
        simOnlyPlanUtility = new Utility_AppendDataToFeatureFile();
        rows = simOnlyPlanUtility.readDataSourceFileIntoList(getFileName());
    }

    protected void print(List<String> fileInputRowsList) {
        System.out.println(fileInputRowsList);
    }

    protected void print(String value) {
        System.out.println(value);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
