package com.company.gherkin_file_appender.unit_tests.config;

import com.company.gherkin_file_appender.config.Prototype_AppendDataToFeatureFileUtility;
import com.company.gherkin_file_appender.config.ResultSelection;
import org.junit.Before;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestBase {

    protected Prototype_AppendDataToFeatureFileUtility simOnlyPlanUtility;
    protected List<String> rows;
    private String fileName;

    public TestBase() {
        setFileName("SampleData.csv");
    }

    @Before
    public void beforeTest() {
        simOnlyPlanUtility = new Prototype_AppendDataToFeatureFileUtility();
        rows = simOnlyPlanUtility.readDataSourceFileIntoList(getFileName());
    }

    protected void print(String[][] fileInput2DArray) {
        System.out.println(Arrays.stream(fileInput2DArray)
                .map(x -> Arrays.stream(x).findAny().get())
                .collect(Collectors.toList()));
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
