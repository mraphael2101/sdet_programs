package com.company.gherkin_file_appender.unit_tests.config;

import com.company.gherkin_file_appender.config.Prototype_AppendDataToFeatureFileUtility;
import org.junit.Before;

import java.util.Arrays;
import java.util.List;

public class TestBase {

    protected Prototype_AppendDataToFeatureFileUtility utility2;
    protected List<String> rows;
    protected String[][] sampleData;

    @Before
    public void beforeTest() {
        utility2 = new Prototype_AppendDataToFeatureFileUtility();
        rows = utility2.readDataSourceFileIntoList("Sample_Data.csv");
    }

    protected String[][] initExpected2DArr(int rowSize, int colSize) {
        String[][] sampleData = new String[rowSize][colSize];
        int rowTemp = 1;
        int colTemp = 1;

        for(int c = 0; c < colSize; c++) {
            sampleData[0][c] = String.format("Column %d", c + colTemp);
        }

        for(int r = 1; r < rowSize; r++) {
            for(int c = 0; c < colSize; c++) {
                sampleData[r][c] = String.format("Row %d Col %d", rowTemp,  colTemp++);
            }
            rowTemp += 1;
            colTemp = 1;
        }
        return sampleData;
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
