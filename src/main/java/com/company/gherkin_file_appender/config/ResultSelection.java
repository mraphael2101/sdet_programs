package com.company.gherkin_file_appender.config;

import java.util.Arrays;
import java.util.Comparator;

public class ResultSelection {
    private final int rowIndex;
    private final String[] row;

    public ResultSelection(int rowIndex, String[] row) {
        this.rowIndex = rowIndex;
        this.row = row;
    }

    @Override
    public boolean equals(Object obj) {
        /*
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof ResultSelection)) {
            return false;
        }
        var objResultSelection = (ResultSelection) obj;
        return rowIndex == objResultSelection.rowIndex && Arrays.equals(row, objResultSelection.row, Comparator.naturalOrder());
        */
        if(obj instanceof ResultSelection) {
            return true;
        }
        else {
            return false;
        }
    }

    // This is only executed when an instance of the class is instantiated
    @Override
    public String toString() {
        return String.format("%d: %s", rowIndex, Arrays.toString(row));
    }
}