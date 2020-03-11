package com.iplanalyzer;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IPLAnalyzerTest {


    public static final String IPL_BATSMAN_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_BOWLER_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

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

    @Test
    public void givenIPLBatsman_WhenSortedOnStrikingRates_ShouldReturnSortedResult() {
        try {
            IPLAnalyzer iplAnalyzer = new IPLAnalyzer();
            iplAnalyzer.loadIPLBatsmenData(IPL_BATSMAN_CSV_FILE_PATH);
            String sortedData = iplAnalyzer.getStatWiseSortedData(SortField.STRIKING_RATE);
            IPLDTO[] censusCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals(333.33, censusCSV[0].strikeRate, 0.0);
        } catch (StatAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBatsman_WhenSortedOnFourAndSix_ShouldReturnSortedResult() {
        try {
            IPLAnalyzer iplAnalyzer = new IPLAnalyzer();
            iplAnalyzer.loadIPLBatsmenData(IPL_BATSMAN_CSV_FILE_PATH);
            String sortedData = iplAnalyzer.getStatWiseSortedData(SortField.FOUR_AND_SIX);
            IPLDTO[] censusCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals(83, censusCSV[0].numOfFour + censusCSV[0].numOfSix);
        } catch (StatAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBatsman_WhenSortedOnFourAndSix_ShouldReturnStrikeRateResult() {
        try {
            IPLAnalyzer iplAnalyzer = new IPLAnalyzer();
            iplAnalyzer.loadIPLBatsmenData(IPL_BATSMAN_CSV_FILE_PATH);
            String sortedData = iplAnalyzer.getStatWiseSortedData(SortField.FOUR_AND_SIX);
            IPLDTO[] censusCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals(204.81, censusCSV[0].strikeRate, 0.0);
        } catch (StatAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBatsman_WhenSortedOnAverage_ShouldReturnStrikeRateResult() {
        try {
            IPLAnalyzer iplAnalyzer = new IPLAnalyzer();
            iplAnalyzer.loadIPLBatsmenData(IPL_BATSMAN_CSV_FILE_PATH);
            String sortedData = iplAnalyzer.getStatWiseSortedData(SortField.AVG);
            IPLDTO[] censusCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals(134.62, censusCSV[0].strikeRate, 0.0);
        } catch (StatAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBatsman_WhenSortedOnAverage_ShouldReturnRunResult() {
        try {
            IPLAnalyzer iplAnalyzer = new IPLAnalyzer();
            iplAnalyzer.loadIPLBatsmenData(IPL_BATSMAN_CSV_FILE_PATH);
            String sortedData = iplAnalyzer.getStatWiseSortedData(SortField.AVG);
            IPLDTO[] censusCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals(416, censusCSV[0].runs);
        } catch (StatAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //Test cases for Bowling Stat Files
    @Test
    public void givenIPLBowlerCSVFileReturnCorrectRecords() {
        IPLAnalyzer iplAnalyzer = new IPLAnalyzer();
        int numOfEnteries = iplAnalyzer.loadIPLBowlerData(IPL_BOWLER_CSV_FILE_PATH);
        Assert.assertEquals(99, numOfEnteries);
    }

    @Test
    public void givenIPLBowlerCSVData_WithWrongFile_ShouldThrowException() {
        try {
            IPLAnalyzer iplAnalyzer = new IPLAnalyzer();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StatAnalyzerException.class);
            iplAnalyzer.loadIPLBowlerData(WRONG_CSV_FILE_PATH);
        } catch (StatAnalyzerException e) {
            Assert.assertEquals(StatAnalyzerException.ExceptionType.STAT_FILE_PROBLEM, e.type);
        }
    }
}
