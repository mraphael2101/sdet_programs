package com.company.sdet_programs.two_dim_array;

import java.util.Arrays;

public class _01_CopySubsetOfTwoDimArray {
    static int[][] fileDataAsTwoDimArr = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
    static int[][] a = new int[fileDataAsTwoDimArr.length][];

    public static void main(String[] args) {
        for (int i = 0; i < fileDataAsTwoDimArr.length; i++) {
            a[i] = Arrays.copyOfRange(fileDataAsTwoDimArr[i], 1, 3);
        }
        System.out.println(Arrays.deepToString(a));
    }

}
