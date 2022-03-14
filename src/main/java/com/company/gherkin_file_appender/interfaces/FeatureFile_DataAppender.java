package com.company.gherkin_file_appender.interfaces;

import java.util.List;

public interface FeatureFile_DataAppender {

    List<String> readDataSourceFileIntoList(String fileName);

    String[][] readCleanseDataSourceFileInto2DArray(String fileName, boolean cleanseSwitch);

    boolean copyFeatureFile(String fileName);

    boolean appendDataToNewFeatureFile(String mode, int... rowRange);

}
