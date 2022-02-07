package junit_parameterization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class _01_CSV_Reader {
    private static final int FIELD_1_INDEX = 0;

    public static void main(String[] args) {
        BufferedReader fileReader = null;
        String line = "";
        try {
            fileReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\SampleData.csv"));
            fileReader.readLine();

            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(",");
                if(tokens.length > 0) {
                    if(tokens[FIELD_1_INDEX].contains("a\'")) {
                        // do something e.g. cleansing happens here
                    }
                }
            }
            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
