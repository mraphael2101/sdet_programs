package com.company.gherkin_file_appender.unit_tests.config;

import com.company.gherkin_file_appender.config.Prototype_AppendDataToFeatureFileUtility;
import com.company.gherkin_file_appender.unit_tests.config.pojo.sample_data_tab.SampleData;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class TestBase_Mocked {
    protected Prototype_AppendDataToFeatureFileUtility utility2;
    protected List<String> rows;

    @InjectMocks
    protected Prototype_AppendDataToFeatureFileUtility utility;

    @Mock
    protected SampleData sampleData;

    @BeforeClass
    public void beforeTestSuite() {
        MockitoAnnotations.openMocks(this);
    }

    @Before
    public void beforeTest() {
        utility2 = new Prototype_AppendDataToFeatureFileUtility();
        rows = utility2.readDataSourceFileIntoList("Sample_Data.csv");

        //String[][] expected2DArr = new SampleData().getSampleData();
    }


    protected void print(String value) {
        System.out.println(value);
    }

    protected void print(String[][] inputFileAsTwoDimArr) {
        System.out.println(Arrays.deepToString(inputFileAsTwoDimArr));
    }

    protected void print(List<String> fileInputRowsList) {
        System.out.println(fileInputRowsList);
    }
}
