package com.company.sdet_programs.two_dim_array.predicatesAndStreams;

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
        if(this == obj)
            return true;
        if(obj == null || ! (obj instanceof ResultSelection))
            return false;
        var p = (ResultSelection) obj;
        return rowIndex == p.rowIndex && Arrays.equals(row, p.row, Comparator.naturalOrder());
    }

    @Override
    public String toString() {
        return String.format("%d: %s", rowIndex, Arrays.toString(row));
    }
}