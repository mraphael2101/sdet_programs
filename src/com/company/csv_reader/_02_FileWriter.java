package com.company.csv_reader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class _02_FileWriter {
    private static final String NEW_LINE = System.lineSeparator();

    public static void main(String[] args) {
        try {
            Path path = Paths.get(System.getProperty("user.dir")
                    + "\\src\\com\\company\\csv_reader\\MyOutput.csv");
            writeFile(path, "Hello World 1" + NEW_LINE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(Path path, String content) throws IOException {
        // file does not exist, create and write it
        // if the file exists, override the content
        Files.write(path, content.getBytes(StandardCharsets.UTF_8));

        // For append mode, we can define both StandardOpenOption.CREATE and StandardOpenOption.APPEND
        //Files.write(path, content.getBytes(StandardCharsets.UTF_8),
        //        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
