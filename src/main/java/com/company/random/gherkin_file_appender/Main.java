package com.company.random.gherkin_file_appender;

public class Main {

    public static void main(String[] args) {
        _01_Rewrite_FeatureFile_Utility simOnlyPlanUtility = new _01_Rewrite_FeatureFile_Utility();

        simOnlyPlanUtility.readAndCleanseDataFile("SampleData.csv", 8, 4);

        simOnlyPlanUtility.createRevisedGherkinFile("SampleData.csv", "demo2.feature");

        //System.out.println(simOnlyPlanUtility.getSingleRowAndColumns(0,4));

        //System.out.println(simOnlyPlanUtility.getMultipleRowsAndColumns(1,4));

        System.exit(0);
    }

}
