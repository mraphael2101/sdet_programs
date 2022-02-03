package com.company.csv_reader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class _01_FileReader {
    public static void main(String[] args) {
        try {
            FileInputStream fin = new FileInputStream(System.getProperty("user.dir")
                    + "\\src\\com\\company\\csv_reader\\SampleData.csv");
            BufferedInputStream bin = new BufferedInputStream(fin);
            int i = 0;
            while ((i = bin.read()) != -1)
                System.out.print((char) i);
            bin.close();
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
