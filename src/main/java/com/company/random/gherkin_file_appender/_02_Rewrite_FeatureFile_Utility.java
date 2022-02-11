package com.company.random.gherkin_file_appender;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class _02_Rewrite_FeatureFile_Utility {
    static List<String> fileContentsAsList, fileSubsetContentsAsList = new ArrayList<>();
    static String rowset;

    public static void main(String[] args) {
        readFileIntoList("SampleData.csv");

        System.out.println("");
        System.out.println("");

        System.out.println("\nSpecific RowSet Range of interest");
        getMultipleRows(2,3);
        for (String val : fileSubsetContentsAsList)
            System.out.println("Row = " + val);

        System.out.println("\nSpecific RowSet of interest");
        System.out.println(getSpecificRow(4));
    }

    private static List<String> readFileIntoList(String fileName) {
        try {
            fileContentsAsList = Files.readAllLines(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\" + fileName).toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContentsAsList;
    }

    private static String getSpecificRow(int rowIndex) {
        for (int i = 0; i < fileContentsAsList.size(); i++) {
            if (i == rowIndex) {
                return rowset = fileContentsAsList.get(i);
            }
        }
        return null;
    }

    private static List<String> getMultipleRows(int rangeStart, int rangeEnd) {
        for (int i = 0; i < fileContentsAsList.size(); i++) {
            if (i >= rangeStart && i <= rangeEnd) {
                fileSubsetContentsAsList.add(fileContentsAsList.get(i));
            }
        }
        return fileSubsetContentsAsList;
    }
}
