package com.company.gherkin_file_appender.config;

import com.company.gherkin_file_appender.interfaces.FeatureFile_DataAppender;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.Arrays.copyOf;
import static java.util.stream.Collectors.toList;

public class Prototype_AppendDataToFeatureFileUtility implements FeatureFile_DataAppender {
    private final String USER_DIR = System.getProperty("user.dir");
    private final String LINE_SEPARATOR = System.lineSeparator();
    private final List<String> inputFileSubsetAsList;
    private List<String> inputFileAsList;
    private String[][] inputFileSubsetAsTwoDimArr;
    private String[][] inputFileAsTwoDimArr;
    private String fileName;

    public Prototype_AppendDataToFeatureFileUtility() {
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

        for (String[] row : inputFileAsTwoDimArr) {
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
        } finally {
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
    public boolean appendDataToNewFeatureFile(String mode, int... range) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(getFileName(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            String firstRow = "|" + getInputFileAsList().get(0).replaceAll(",", "|") + "|" + LINE_SEPARATOR;

            switch (mode.toLowerCase()) {
                case "alldata":
                    for (String str : getInputFileAsList()) {
                        bw.write("|" + str.replace(",", "|") + "|" + LINE_SEPARATOR);
                    }
                    break;
                case "rowsrange":
                    if (range.length == 2) {
                        if(!(range[0] == 0)) {
                            bw.write(firstRow);
                        }
                        for (String str : getRowRangeFromInputFileArrayList(range[0], range[1])) {
                            bw.write("|" + str.replace(",", "|") + "|" + LINE_SEPARATOR);
                        }
                    }
                    else if(range.length > 2) {
                        throw new RuntimeException("A range cannot have more than two values");
                    }
                    break;
                case "row":
                    bw.write(firstRow);
                    String rowset = getSpecificRowFromInputFileArrayList(range[0]);
                    bw.write("|" + rowset.replace(",", "|") + "|" + LINE_SEPARATOR);
                    break;
            }
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getSpecificRowFromInputFileArrayList(int rowIndex) {
        for (int i = 0; i < inputFileAsList.size(); i++) {
            if (i == rowIndex - 1) {
                return inputFileAsList.get(i);
            }
        }
        return null;
    }

    public List<String> getRowRangeFromInputFileArrayList(int rangeStart, int rangeEnd) {
        inputFileSubsetAsList.clear();
        for (int i = 0; i < inputFileAsList.size(); i++) {
            if (i >= rangeStart && i <= rangeEnd) {
                inputFileSubsetAsList.add(inputFileAsList.get(i));
            }
        }
        return inputFileSubsetAsList;
    }

    public List<String> getRowSubsetFromInputFile2DArray(long lastRowIndex) {
        inputFileSubsetAsTwoDimArr = Arrays.stream(inputFileAsTwoDimArr)
                .limit(lastRowIndex)
                .toArray(String[][]::new);

        inputFileSubsetAsList.clear();

        for (int i = 0; i < inputFileSubsetAsTwoDimArr.length; i++) {
            for (int j = 0; j < inputFileSubsetAsTwoDimArr[0].length; j++)
                inputFileSubsetAsList.add(inputFileSubsetAsTwoDimArr[i][j]);
        }
        return inputFileSubsetAsList;
    }

    public List<ResultSelection> filterRowsByList(String[][] array, Predicate<String> predicate, int column) {
        return IntStream.range(0, array.length)
                .filter(i -> predicate.test(array[i][column]))
                .mapToObj(i -> new ResultSelection(i, copyOf(array[i], array[i].length)))
                .collect(toList());
    }

    public Map<Integer, String[]> filterRowsByMap(String[][] array, Predicate<String> predicate, int column) {
        TreeMap<Integer, String[]> result = new TreeMap<>();
        for (int i = 0; i < array.length; i++) {
            if (predicate.test(array[i][column])) {
                result.put(i, copyOf(array[i], array[i].length));
            }
        }
        return result;
    }

    public List<String> getColumnSubsetFromInputFileArrayList(int firstColIndex, int lastColIndex) {
        inputFileSubsetAsTwoDimArr = Arrays.copyOfRange(inputFileAsTwoDimArr, firstColIndex, lastColIndex);
        return inputFileSubsetAsList;
    }

    public List<String> getColumnSubsetFromInputFile2DArray() {
        return null;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getInputFileAsList() {
        return this.inputFileAsList;
    }

    public String getPartialInputFilePath() {
        String PARTIAL_INPUT_FILE_PATH = "\\src\\test\\resources\\input_data\\";
        if(System.getProperty("os.name").contains("Windows")) {
            return PARTIAL_INPUT_FILE_PATH;
        }
        else {
            return PARTIAL_INPUT_FILE_PATH.replaceAll("\\\\", "/");
        }
    }

    public String getPartialOutputFilePath() {
        String PARTIAL_OUTPUT_FILE_PATH = "\\src\\test\\resources\\features\\";
        if(System.getProperty("os.name").contains("Windows")) {
            return PARTIAL_OUTPUT_FILE_PATH;
        }
        else {
            return PARTIAL_OUTPUT_FILE_PATH.replaceAll("\\\\", "/");
        }
    }

}