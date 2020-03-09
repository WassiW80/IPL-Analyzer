package com.iplanalyzer;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IPLAnalyzerTest {


    public static final String IPL_BATSMAN_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenIPLBatsmanCSVFileReturnCorrectRecords() {
        IPLAnalyzer iplAnalyzer = new IPLAnalyzer();
        int numOfEnteries = iplAnalyzer.loadIPLBatsmenData(IPL_BATSMAN_CSV_FILE_PATH);
        Assert.assertEquals(100, numOfEnteries);
    }

    @Test
    public void givenIPLBatsmanCSVData_WithWrongFile_ShouldThrowException() {
        try {
            IPLAnalyzer iplAnalyzer = new IPLAnalyzer();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StatAnalyzerException.class);
            iplAnalyzer.loadIPLBatsmenData(WRONG_CSV_FILE_PATH);
        } catch (StatAnalyzerException e) {
            Assert.assertEquals(StatAnalyzerException.ExceptionType.STAT_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLBatsman_WhenSortedOnAverage_ShouldReturnSortedResult() {
        try {
            IPLAnalyzer iplAnalyzer = new IPLAnalyzer();
            iplAnalyzer.loadIPLBatsmenData(IPL_BATSMAN_CSV_FILE_PATH);
            String sortedData = iplAnalyzer.getStatWiseSortedData(SortField.AVG);
            IPLDTO[] censusCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals(83.2, censusCSV[0].average, 0.0);
        } catch (StatAnalyzerException e) {
            e.printStackTrace();
        }
    }
}
