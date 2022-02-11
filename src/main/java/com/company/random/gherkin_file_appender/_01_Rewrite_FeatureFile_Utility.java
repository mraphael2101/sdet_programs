package com.company.random.gherkin_file_appender;


import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _01_Rewrite_FeatureFile_Utility {
    public String selectedRowAndColumnDataRange = "";
    private List<String> selectedRowsAndColumnsDataRangeList;
    private String[][] fileDataAsTwoDimArr;
    private List<String> inputFileAsList;
    public List<String> datasetAsList = new ArrayList<>();
    private int FIELD_1_INDEX = 0;
    private int FIELD_2_INDEX = 1;
    private int FIELD_3_INDEX = 2;
    private int FIELD_4_INDEX = 3;
    private static final String NEW_LINE = System.lineSeparator();
    private static String userDir = System.getProperty("user.dir");

    public void readAndCleanseDataFile(String fileName, int lastRowIndex, int lastColIndex) {
        BufferedReader fileReader = null;
        String line = "";
        try {
            fileReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\data_tables\\" + fileName));
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(",");
                if(tokens.length > 0) {
                    // Cleansing happens here
                    if(tokens[FIELD_1_INDEX].contains("\t'"))
                        tokens[FIELD_1_INDEX] = tokens[FIELD_1_INDEX].replaceAll("\t'"," ");
                    if(tokens[FIELD_2_INDEX].contains("\t'"))
                        tokens[FIELD_2_INDEX] = tokens[FIELD_2_INDEX].replaceAll("\t'"," ");
                    if(tokens[FIELD_3_INDEX].contains("\t'"))
                        tokens[FIELD_3_INDEX] = tokens[FIELD_3_INDEX].replaceAll("\t'"," ");
                    if(tokens[FIELD_4_INDEX].contains("\t'"))
                        tokens[FIELD_1_INDEX] = tokens[FIELD_4_INDEX].replaceAll("\t'"," ");
                }
                datasetAsList.addAll(Arrays.asList(tokens));
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int rowSize = lastRowIndex - 1;
        int colSize = lastColIndex;
        int k = 0;
        fileDataAsTwoDimArr = new String[rowSize][colSize];

        for (int i=0; i<rowSize; i++) {
            for (int j=0; j<colSize; j++) {
                fileDataAsTwoDimArr[i][j] = datasetAsList.get(k++);
            }
        }
    }

    public String getSingleRowAndColumns(int rowIndex, int columnMaxRange) {
        int rowSize = rowIndex - 1;
        if (rowIndex == 0) {
            rowSize = 0;
        }
        if (rowIndex == 0) {
            for (int j = 0; j < columnMaxRange; j++) {
                selectedRowAndColumnDataRange = selectedRowAndColumnDataRange + " | " + fileDataAsTwoDimArr[0][j];
            }
            return selectedRowAndColumnDataRange = selectedRowAndColumnDataRange + "|";
        } else {
            selectedRowAndColumnDataRange = "";

            String[][] resultset = new String[fileDataAsTwoDimArr.length][];
            for (int i = 0; i < fileDataAsTwoDimArr.length; i++) {
                resultset[i] = Arrays.copyOfRange(fileDataAsTwoDimArr[i], rowIndex, columnMaxRange);

                for (String[] val: resultset) {
                    System.out.println(val);
                }
            }
            return selectedRowAndColumnDataRange;
        }
    }

    public List<String> getMultipleRowsAndColumns(int rowIndex, int columnMaxRange) {
        String[][] resultset = new String[fileDataAsTwoDimArr.length][];
        for (int a = 0; a < fileDataAsTwoDimArr.length; a++) {
            resultset[a] = Arrays.copyOfRange(fileDataAsTwoDimArr[a], rowIndex, columnMaxRange);
        }
        System.out.println(Arrays.deepToString(resultset));
        return datasetAsList;
    }


    public boolean createRevisedGherkinFile(String inputFileName, String outputFileName) {
        /*TODO 1) Read in the Gherkin file
               2) Traverse to the end of the file
               3) \n\n Write rows of data
        */
        try {
            inputFileAsList = Files.readAllLines(new File(userDir + "\\src\\test\\resources\\data_tables\\"
                    + inputFileName).toPath());

            //fileContentsAsList.forEach(System.out::println);
            //fileContentsAsList.stream().parallel().forEach(s -> System.out.println(s));

            FileWriter fw = new FileWriter(userDir + "\\src\\test\\resources\\features\\rewrite_demo\\" + outputFileName);
            BufferedWriter bw = new BufferedWriter(fw);

            for(String s : cleanseListWithPipe()) {
                bw.write(s + System.getProperty("line.separator"));
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private List<String> cleanseListWithPipe() {
        for (String val: inputFileAsList) {
            val.replaceAll(",", " |");
        }
        return inputFileAsList;
    }

}
