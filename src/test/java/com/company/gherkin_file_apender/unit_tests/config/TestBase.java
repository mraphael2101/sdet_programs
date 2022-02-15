package com.company.gherkin_file_apender.unit_tests.config;

import com.company.gherkin_file_appender.config.Prototype_AppendDataToFeatureFileUtility;
import org.junit.Before;

import java.util.List;

public class TestBase {

    //@InjectMocks
    protected Prototype_AppendDataToFeatureFileUtility simOnlyPlanUtility;

    protected List<String> rows;
    private String fileName;

    //@Mock
    //Type1 myType1 = new Type1();
    //@Mock
    //Type2 myType2 = new Type2();

    public TestBase() {
        setFileName("SampleData.csv");
    }

    @Before
    public void beforeTest() {
        //MockitoAnnotations.openMocks(this);
        simOnlyPlanUtility = new Prototype_AppendDataToFeatureFileUtility();
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
