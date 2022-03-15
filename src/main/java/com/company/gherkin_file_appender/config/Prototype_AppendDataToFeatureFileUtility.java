package com.company.gherkin_file_appender.config;

import com.company.gherkin_file_appender.interfaces.FeatureFile_DataAppender;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Predicate;

import static java.util.Arrays.copyOf;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class Prototype_AppendDataToFeatureFileUtility implements FeatureFile_DataAppender {
    private boolean cleanseSwitch;
    private String fileName;
    private String excelTab = "";
    private final String USER_DIR = System.getProperty("user.dir");
    private final String LINE_SEPARATOR = System.lineSeparator();
    private List<String> inputFileSubsetAsList;
    private List<String> inputFileAsList;
    private String[][] inputFileSubsetAsTwoDimArr;
    private String[][] inputFileAsTwoDimArr;

    public Prototype_AppendDataToFeatureFileUtility() {
        this.inputFileAsList = new ArrayList<>();
        this.inputFileSubsetAsList = new ArrayList<>();
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
    public String[][] readCleanseDataSourceFileInto2DArray(String fileName, boolean cleanseSwitch) {
        BufferedReader fileReader = null;
        String line = "";
        long rowCount = 0;
        int colCount = 0;
        String[] tokens = new String[0];
        try {
            fileReader = new BufferedReader(new FileReader(USER_DIR + getPartialInputFilePath() + fileName));
            rowCount = fileReader.lines().count();
            // re-initialise
            fileReader = new BufferedReader(new FileReader(USER_DIR + getPartialInputFilePath() + fileName));
            while ((line = fileReader.readLine()) != null) {
                tokens = line.split(",");
                colCount = tokens.length;
                if (tokens.length > 0) {
                    // Cleansing happens here
                    if(cleanseSwitch) {
                        int FIELD_1_INDEX = 2;
                        if (tokens[FIELD_1_INDEX].contains("2021")) {
                            tokens[FIELD_1_INDEX] = tokens[FIELD_1_INDEX].replace("2021", "");
                        }
                    }
                }
                inputFileAsList.addAll(Arrays.asList(tokens));
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Map List to 2D Array based on the colSize
        inputFileAsTwoDimArr = new String[(int) rowCount][tokens.length];
        int k = 0;
        for (int r = 0; r < rowCount; r++) {
            if (r % 2 == 0) {
                for (int c = 0; c < colCount; c++) {
                    inputFileAsTwoDimArr[r][c] = inputFileAsList.get(k++);
                }
            } else {
                for (int c = 0; c < colCount; c++) {
                    inputFileAsTwoDimArr[r][c] = inputFileAsList.get(k++);
                }
            }
        }
        return inputFileAsTwoDimArr;
    }

    @Override
    public boolean copyFeatureFile(String fileName) {
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            File fromFile = new File(USER_DIR + getPartialOutputFilePath() + fileName);
            File toFile = new File(USER_DIR + getPartialOutputFilePath() + "data_vol_" + fileName);
            setFileName(USER_DIR + getPartialOutputFilePath() + "data_vol_" + fileName.toLowerCase());
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
            String firstRow = getSpecificRowFromInputFile2DArray(0).replaceAll(",", "|") + "|" + LINE_SEPARATOR;
            String rowset = "";
            int columnSize = 0;
            if (range.length == 2) {
                columnSize = range[1] - range[0];
            }
            switch (mode.toLowerCase()) {
                case "alldata":
                    for (String str : getInputFileAsList()) {
                        bw.write("|" + str.replace(",", "|") + "|" + LINE_SEPARATOR);
                    }
                    break;
                case "rowrange":
                    if (range.length == 2) {
                        if (!(range[0] == 0)) {
                            bw.write(firstRow);
                        }
                        for (String[] eachRow : getRowRangeFromInputFile2DArray(range[0], range[1])) {
                            for (String str : eachRow) {
                                bw.write("|" + str.replace(",", "|"));
                            }
                            bw.write(LINE_SEPARATOR);
                        }
                    } else if (range.length > 2) {
                        throw new RuntimeException("A range cannot have more than two values");
                    }
                    break;
                case "row":
                    bw.write(firstRow);
                    rowset = getSpecificRowFromInputFile2DArray(range[0]);
                    bw.write(rowset.replace(",", "|") + "|" + LINE_SEPARATOR);
                    break;
                case "colrange":
                    if (range.length > 2) {
                        throw new RuntimeException("A range cannot have more than two values");
                    }
                    else if (range.length == 2 && ((range[0] == 0) || range[0] == 1)) {
                        for (String[] eachRow : getColumnRangeFromInputFile2DArray(range[0], range[1])) {
                            for (String str : eachRow) {
                                bw.write("|" + str.replace(",", "|"));
                            }
                            bw.write(LINE_SEPARATOR);
                        }
                    } else {
                        bw.write(firstRow);
                        for (String[] eachRow : getColumnRangeFromInputFile2DArray(range[0], range[1])) {
                            for (String str : eachRow) {
                                bw.write("|" + str.replace(",", "|"));
                            }
                            bw.write(LINE_SEPARATOR);
                        }
                    }
                    break;
                case "column":
                    for (String str : getSpecificColumnFromInputFile2DArray(range[0])) {
                        bw.write("|" + str.replace("[", "|")
                                .replace("]", "")
                                .replace(",", "|") + "|" + LINE_SEPARATOR);
                    }
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

    public List<String> getSpecificColumnFromInputFile2DArray(int colIndex) {
        return Arrays.stream(inputFileAsTwoDimArr)
                .map(object -> object[colIndex])
                .collect(toList());
    }

    //TODO Review this method
    public List<String> getColRangeFromInputFile2DArray(int rangeStart, int rangeEnd) {
        int startRange = rangeStart;
        if (rangeStart <= 0) {
            startRange = 1;
        }
        // Mapping of the two-dim array with indices
        inputFileSubsetAsList.clear();
        int finalRangeStart = startRange;
        range(0, inputFileAsTwoDimArr.length)
                .flatMap(row -> range(finalRangeStart, rangeEnd)
                        .map(col -> {
                            inputFileSubsetAsList.add(inputFileAsTwoDimArr[row][col]);
                            return row;
                        }))
                .forEach(row -> {
                });
        return inputFileSubsetAsList;
    }

    public String[][] getColumnRangeFromInputFile2DArray(int rangeStart, int rangeEnd) {
        int colCount = (rangeEnd - rangeStart) + 1;
        try {
            inputFileSubsetAsTwoDimArr = new String[inputFileAsTwoDimArr.length][colCount];
            for(int r = 0; r < inputFileSubsetAsTwoDimArr.length; r++) {
                for(int c = 0; c < colCount; c++) {
                    inputFileSubsetAsTwoDimArr[r][c] = inputFileAsTwoDimArr[r][rangeStart + c].stripLeading();
                }
            }
            return inputFileSubsetAsTwoDimArr;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return  null;
        }
    }

    public String getSpecificRowFromInputFile2DArray(int rowIndex) {
        try {
            String rowset = "";
            for(int i = 0; i < inputFileAsTwoDimArr[0].length; i++) {
                rowset += "|" + inputFileAsTwoDimArr[rowIndex][i];
            }
            return rowset;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[][] getRowRangeFromInputFile2DArray(int rangeStart, int rangeEnd) {
        int rowCount = (rangeEnd - rangeStart) + 1;
        try {
            inputFileSubsetAsTwoDimArr = new String[rowCount][inputFileAsTwoDimArr[0].length];
            for(int r = 0; r < rowCount; r++) {
                for(int c = 0; c < inputFileAsTwoDimArr[0].length; c++) {
                    inputFileSubsetAsTwoDimArr[r][c] = inputFileAsTwoDimArr[rangeStart + r][c];
                }
            }
            return inputFileSubsetAsTwoDimArr;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return  null;
        }
    }

    public List<ResultSelection> filterRowsByList(String[][] array, Predicate<String> predicate, int column) {
        return range(0, array.length)
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
        if (System.getProperty("os.name").contains("Windows")) {
            return PARTIAL_INPUT_FILE_PATH;
        } else {
            return PARTIAL_INPUT_FILE_PATH.replaceAll("\\\\", "/");
        }
    }

    public String getPartialOutputFilePath() {
        String PARTIAL_OUTPUT_FILE_PATH = "\\src\\test\\resources\\features\\" + getExcelTab() + "\\";
        if (System.getProperty("os.name").contains("Windows")) {
            return PARTIAL_OUTPUT_FILE_PATH;
        } else {
            return PARTIAL_OUTPUT_FILE_PATH.replaceAll("\\\\", "/");
        }
    }

    public String getExcelTab() {
        return excelTab;
    }

    public void setExcelTab(String excelTab) {
        this.excelTab = excelTab;
    }

    public boolean isCleanseSwitch() {
        return cleanseSwitch;
    }

    public void setCleanseSwitch(boolean cleanseSwitch) {
        this.cleanseSwitch = cleanseSwitch;
    }

}