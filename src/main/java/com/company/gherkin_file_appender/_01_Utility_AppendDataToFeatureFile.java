package com.company.gherkin_file_appender;

import com.company.gherkin_file_appender.interfaces._01_FeatureFile_DataAppender;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _01_Utility_AppendDataToFeatureFile implements _01_FeatureFile_DataAppender {
    private final String USER_DIR = System.getProperty("user.dir");
    private String PARTIAL_INPUT_FILE_PATH = "\\src\\test\\resources\\input_data\\";
    private String PARTIAL_OUTPUT_FILE_PATH = "\\src\\test\\resources\\features\\";
    private final String LINE_SEPARATOR = System.lineSeparator();
    private List<String> inputFileAsList;
    private List<String> inputFileSubsetAsList;
    private String[][] inputFileAsTwoDimArr, inputFileSubsetAsTwoDimArr;
    private String fileName;

    public _01_Utility_AppendDataToFeatureFile() {
        this.inputFileAsList = new ArrayList<>();
        this.inputFileSubsetAsList = new ArrayList<>();
    }

    public List<String> readAndCleanseInputDataFile(String fileName, int lastRowIndex, int lastColIndex) {
        BufferedReader fileReader = null;
        String line = "";
        try {
            fileReader = new BufferedReader(new FileReader(USER_DIR + getPartialInputFilePath() + fileName));
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

    @Override
    public List<String> readDataSourceFileIntoList(String fileName) {
        try {
            inputFileAsList = Files.readAllLines(new File(USER_DIR + getPartialInputFilePath() + fileName).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputFileAsList;
    }

    @Override
    public boolean copyFeatureFile(String fileName) {
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            File fromFile = new File(USER_DIR + getPartialOutputFilePath()+ fileName);
            File toFile = new File(USER_DIR + getPartialOutputFilePath() + "data_vol_" + fileName);
            setFileName(USER_DIR + getPartialOutputFilePath() + "data_vol_" + fileName);
            inStream = new FileInputStream(fromFile);
            outStream = new FileOutputStream(toFile);
            byte[] buffer = new byte[1024];

            int length;
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
                outStream.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        finally {
            try {
                if (inStream != null)
                    inStream.close();
                if (outStream != null)
                    outStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean appendDataToNewFeatureFile() {
        FileWriter fw = null;
        try {
            fw = new FileWriter(getFileName(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            for(String str: getInputFileAsList()) {
                bw.write("|" + str.replace(",","|") + "|" + System.lineSeparator());
            }
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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

    public String[][] selectRows(String[][] array, List<Integer> indices) {
        return indices.stream().map(i -> array[i]).toArray(String[][]::new);
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getInputFileAsList() {
        return this.inputFileAsList ;
    }

    public String getPartialInputFilePath() {
        if(System.getProperty("os.name").contains("Windows")) {
            return this.PARTIAL_INPUT_FILE_PATH;
        }
        else {
            return this.PARTIAL_INPUT_FILE_PATH.replaceAll("\\\\", "/");
        }
    }

    public String getPartialOutputFilePath() {
        if(System.getProperty("os.name").contains("Windows")) {
            return this.PARTIAL_OUTPUT_FILE_PATH;
        }
        else {
            return this.PARTIAL_OUTPUT_FILE_PATH.replaceAll("\\\\", "/");
        }
    }

}
