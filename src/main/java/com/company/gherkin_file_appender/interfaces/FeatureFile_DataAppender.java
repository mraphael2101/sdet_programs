package com.company.gherkin_file_appender.interfaces;

import java.util.List;

public interface FeatureFile_DataAppender {

    String[][] readCleanseDataSourceFileInto2DArray(String fileName, boolean cleanseSwitch);

    boolean copyFeatureFile(String fileName);

    /***
     Example usages:
     a) "row",                      Object[] args = { 3 }
     b) "rowrange",                 Object[] args = { 1 , 5 }
     c) "column",                   Object[] args = { 1 }
     d) "colrange",                 Object[] args = { 1 , 3 }
     e) "filtered_list",            Object[] args = { predicate, intQueryColIndex }
     f) "filtered_map_by_col",      Object[] args = { predicate, intQueryColIndex, intResultColIndex }
     g) "filtered_map_by_colrange", Object[] args = { predicate, intQueryColIndex, intResultStartColIndex, intResultEndColIndex }
     ***/
    boolean appendDataToNewFeatureFile(String mode, Object... args);

}
