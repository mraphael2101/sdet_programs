package com.company.gherkin_file_appender;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _02_Rewrite_FeatureFile_Utility {
    private final String userDir = System.getProperty("user.dir");
    private final String inputFilePath = "/src/test/resources/input_data/";
    private final String outputFilePath = "/src/test/resources/features/";
    private List<String> inputFileAsList;
    private List<String> inputFileSubsetAsList;
    private String[][] inputFileAsTwoDimArr;

    public _02_Rewrite_FeatureFile_Utility() {
        this.inputFileAsList = new ArrayList<>();
        this.inputFileSubsetAsList = new ArrayList<>();
    }

    public List<String> readAndCleanseInputDataFile(String fileName, int lastRowIndex, int lastColIndex) {
        BufferedReader fileReader = null;
        String line = "";
        try {
            fileReader = new BufferedReader(new FileReader(userDir + inputFilePath + fileName));
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length > 0) {
                    // Cleansing happens here
                    int FIELD_1_INDEX = 0;
                    if (tokens[FIELD_1_INDEX].contains("\t"))
                        tokens[FIELD_1_INDEX] = tokens[FIELD_1_INDEX].replace("\t", "");
                    int FIELD_2_INDEX = 1;
                    if (tokens[FIELD_2_INDEX].contains("\t"))
                        tokens[FIELD_2_INDEX] = tokens[FIELD_2_INDEX].replace("\t", "");
                    int FIELD_3_INDEX = 2;
                    if (tokens[FIELD_3_INDEX].contains("\t"))
                        tokens[FIELD_3_INDEX] = tokens[FIELD_3_INDEX].replace("\t", "");
                    int FIELD_4_INDEX = 3;
                    if (tokens[FIELD_4_INDEX].contains("\t"))
                        tokens[FIELD_4_INDEX] = tokens[FIELD_4_INDEX].replace("\t", "");
                }
                inputFileAsList.addAll(Arrays.asList(tokens));
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        inputFileAsTwoDimArr = new String[lastRowIndex - 1][lastColIndex];
        int k = 0;
        try {
            for (int i = 0; i < lastRowIndex - 1; i++) {
                for (int j = 0; j < lastColIndex; j++) {
                    inputFileAsTwoDimArr[i][j] = inputFileAsList.get(k++);
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Mismatch between lastRowIndex and/or lastColIndex params, and the no of input file records and/or columns");
        }
        for(String[] row: inputFileAsTwoDimArr) {
            for (String s : row) {
                inputFileSubsetAsList.add(s);
            }
        }
        return inputFileSubsetAsList;
    }

    public List<String> readFileIntoList(String fileName) {
        try {
            inputFileAsList = Files.readAllLines(new File(userDir + inputFilePath + fileName).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputFileAsList;
    }

    public boolean createRevisedOutputGherkinFile(String inputFileName, String outputFileName) {
        try {
            inputFileAsList = Files.readAllLines(new File(userDir + inputFilePath + inputFileName).toPath());
            FileWriter fw = new FileWriter(userDir + outputFilePath + outputFileName);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String s : inputFileAsList) {
                bw.write(s + System.getProperty("line.separator"));
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String getSpecificRow(int rowIndex) {
        for (int i = 0; i < inputFileAsList.size(); i++) {
            if (i == rowIndex) {
                return inputFileAsList.get(i);
            }
        }
        return null;
    }

    public List<String> getRowRange(int rangeStart, int rangeEnd) {
        for (int i = 0; i < inputFileAsList.size(); i++) {
            if (i >= rangeStart && i <= rangeEnd) {
                inputFileSubsetAsList.add(inputFileAsList.get(i));
            }
        }
        return inputFileSubsetAsList;
    }
}