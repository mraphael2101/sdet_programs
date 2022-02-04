package junit_parameterization;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class _01_FileReader {
    public void readFile(String filePath) {
        try {
            FileInputStream fin = new FileInputStream(filePath);
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
