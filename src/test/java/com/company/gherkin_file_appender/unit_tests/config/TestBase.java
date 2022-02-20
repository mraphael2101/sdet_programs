package com.company.gherkin_file_appender.unit_tests.config;

import com.company.gherkin_file_appender.config.Prototype_AppendDataToFeatureFileUtility;
import org.junit.Before;

import java.util.List;

public class TestBase {

    protected Prototype_AppendDataToFeatureFileUtility utility2;
    protected List<String> rows;

    @Before
    public void beforeTest() {
        utility2 = new Prototype_AppendDataToFeatureFileUtility();
        rows = utility2.readDataSourceFileIntoList("SampleData.csv");
    }


    protected void print(String value) {
        System.out.println(value);
    }

    protected void print(String[][] fileInput2DArray) {
        //TODO
    }

    protected void print(List<String> fileInputRowsList) {
        System.out.println(fileInputRowsList);
    }

}
