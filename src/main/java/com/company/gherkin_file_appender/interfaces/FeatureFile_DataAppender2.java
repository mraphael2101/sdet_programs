package com.company.gherkin_file_appender.interfaces;

import java.util.List;

public interface FeatureFile_DataAppender2 {

    List<String> readDataSourceFileIntoList(String fileName);

    boolean copyFeatureFile(String fileName);

    boolean appendDataToNewFeatureFile(String mode, int... range);

}
