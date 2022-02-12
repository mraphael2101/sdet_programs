package com.company.gherkin_file_appender;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _01_Rewrite_FeatureFile_Utility {
    private final String userDir = System.getProperty("user.dir");
    private final String inputFilePath = "/src/test/resources/input_data/";
    private final String outputFilePath = "/src/test/resources/features/";
    private List<String> inputFileAsList = new ArrayList<>();
    private List<String> inputFileSubsetAsList;
    private String[][] inputFileAsTwoDimArr, inputFileSubsetAsTwoDimArr;

    public _01_Rewrite_FeatureFile_Utility() {
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

    public List<String> getColumnSubsetOfInputFile(int firstColIndex, int lastColIndex) {
        inputFileSubsetAsTwoDimArr = Arrays.copyOfRange(inputFileAsTwoDimArr, firstColIndex, lastColIndex);
        return inputFileSubsetAsList;
    }

    public List<String> getRowSubsetOfInputFile(long lastRowIndex, long... skip) {
        if (skip != null && skip.length != 0) {
            if (skip[0] == lastRowIndex) {
                try {
                    throw new Exception("You cannot skip a row if it has the same index as the last row");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                inputFileSubsetAsTwoDimArr = Arrays.stream(inputFileAsTwoDimArr)
                        .skip(skip[0])
                        .limit(lastRowIndex)
                        .toArray(String[][]::new);
            }
        } else {
            inputFileSubsetAsTwoDimArr = Arrays.stream(inputFileAsTwoDimArr)
                    .limit(lastRowIndex)
                    .toArray(String[][]::new);
        }
        for (int i = 0; i < inputFileSubsetAsTwoDimArr.length; i++) {
            for (int j = 0; j < inputFileAsTwoDimArr[0].length; j++)
                inputFileSubsetAsList.add(inputFileSubsetAsTwoDimArr[i][j]);
        }
        return inputFileSubsetAsList;
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

    public String[][] selectRows(String[][] array, List<Integer> indices) {
        return indices.stream().map(i -> array[i]).toArray(String[][]::new);
    }

/*
    int indices = {...};
List<String[]> rowList = new ArrayList<>();
for (int i : indices)
{
   rowList.add(myArray[i]);
}
*/
}
