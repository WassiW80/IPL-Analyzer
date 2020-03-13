package com.iplanalyzer;

import com.csvparser.CSVBuilderException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class IPLAnalyzerTest {

    private static IPLAnalyzer iplAnalyzer;
    private String sortedData;
    private IPLDTO[] statisticCSV;
    private static final String IPL_BATSMAN_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_BOWLER_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @BeforeClass
    public static void setUp() {
        iplAnalyzer = new IPLAnalyzer();
    }

    @Test
    public void givenIPLBatsmanCSVFileReturnCorrectRecords() {
        int numOfEnteries = iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BATSMAN, IPL_BATSMAN_CSV_FILE_PATH);
        Assert.assertEquals(100, numOfEnteries);
    }

    @Test
    public void givenIPLBatsmanCSVData_WithWrongFile_ShouldThrowException() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BATSMAN, WRONG_CSV_FILE_PATH);
        } catch (StatisticsAnalyzerException statisticsAnalyzerException) {
            Assert.assertEquals(StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM, statisticsAnalyzerException.type);
        }
    }

    @Test
    public void givenIPLBatsman_WhenSortedOnAverage_ShouldReturnSortedResult() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BATSMAN, IPL_BATSMAN_CSV_FILE_PATH);
            sortedData = iplAnalyzer.getStatWiseSortedData(SortField.AVG);
            statisticCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals("MS Dhoni", statisticCSV[0].player);
        } catch (StatisticsAnalyzerException statisticsAnalyzerException) {
            Assert.assertEquals(StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM, statisticsAnalyzerException.type);
        }
    }

    @Test
    public void givenIPLBatsman_WhenSortedOnStrikingRates_ShouldReturnSortedResult() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BATSMAN, IPL_BATSMAN_CSV_FILE_PATH);
            sortedData = iplAnalyzer.getStatWiseSortedData(SortField.STRIKING_RATE);
            statisticCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals("Ishant Sharma", statisticCSV[0].player);
        } catch (StatisticsAnalyzerException statisticsAnalyzerException) {
            Assert.assertEquals(StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM, statisticsAnalyzerException.type);
        }
    }

    @Test
    public void givenIPLBatsman_WhenSortedOnFourAndSix_ShouldReturnSortedResult() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BATSMAN, IPL_BATSMAN_CSV_FILE_PATH);
            sortedData = iplAnalyzer.getStatWiseSortedData(SortField.FOUR_AND_SIX);
            statisticCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals("Andre Russell", statisticCSV[0].player);
        } catch (StatisticsAnalyzerException statisticsAnalyzerException) {
            Assert.assertEquals(StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM, statisticsAnalyzerException.type);
        }
    }

    @Test
    public void givenIPLBatsman_WhenSortedOnFourAndSix_ShouldReturnStrikeRateResult() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BATSMAN, IPL_BATSMAN_CSV_FILE_PATH);
            sortedData = iplAnalyzer.getStatWiseSortedData(SortField.FOUR_AND_SIX);
            statisticCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals(204.81, statisticCSV[0].strikeRate, 0.0);
        } catch (StatisticsAnalyzerException statisticsAnalyzerException) {
            Assert.assertEquals(StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM, statisticsAnalyzerException.type);
        }
    }

    @Test
    public void givenIPLBatsman_WhenSortedOnAverage_ShouldReturnStrikeRateResult() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BATSMAN, IPL_BATSMAN_CSV_FILE_PATH);
            sortedData = iplAnalyzer.getStatWiseSortedData(SortField.AVG);
            statisticCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals("MS Dhoni", statisticCSV[0].player);
        } catch (StatisticsAnalyzerException statisticsAnalyzerException) {
            Assert.assertEquals(StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM, statisticsAnalyzerException.type);
        }
    }

    @Test
    public void givenIPLBatsman_WhenSortedOnAverage_ShouldReturnRunResult() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BATSMAN, IPL_BATSMAN_CSV_FILE_PATH);
            sortedData = iplAnalyzer.getStatWiseSortedData(SortField.RUN);
            statisticCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals("David Warner", statisticCSV[0].player);
        } catch (StatisticsAnalyzerException statisticsAnalyzerException) {
            Assert.assertEquals(StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM, statisticsAnalyzerException.type);
        }
    }

    //Test cases for Bowling Stat Files
    @Test
    public void givenIPLBowlerCSVFileReturnCorrectRecords() {
        int numOfEnteries = iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BOWLER, IPL_BOWLER_CSV_FILE_PATH);
        Assert.assertEquals(99, numOfEnteries);
    }

    @Test
    public void givenIPLBowlerCSVData_WithWrongFile_ShouldThrowException() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BOWLER, WRONG_CSV_FILE_PATH);
        } catch (StatisticsAnalyzerException e) {
            Assert.assertEquals(StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLBowler_WhenSortedOnAverage_ShouldReturnSortedResult() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BOWLER, IPL_BOWLER_CSV_FILE_PATH);
            sortedData = iplAnalyzer.getStatWiseSortedData(SortField.AVG);
            statisticCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals("Krishnappa Gowtham", statisticCSV[0].player);
        } catch (StatisticsAnalyzerException statisticsAnalyzerException) {
            Assert.assertEquals(StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM, statisticsAnalyzerException.type);
        }
    }

    @Test
    public void givenIPLBowler_WhenSortedOnStrikeRate_ShouldReturnSortedResult() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BOWLER, IPL_BOWLER_CSV_FILE_PATH);
            sortedData = iplAnalyzer.getStatWiseSortedData(SortField.STRIKING_RATE);
            statisticCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals("Krishnappa Gowtham", statisticCSV[0].player);
        } catch (StatisticsAnalyzerException statisticsAnalyzerException) {
            Assert.assertEquals(StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM, statisticsAnalyzerException.type);
        }
    }

    @Test
    public void givenIPLBowler_WhenSortedOnEconomy_ShouldReturnSortedResult() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BOWLER, IPL_BOWLER_CSV_FILE_PATH);
            sortedData = iplAnalyzer.getStatWiseSortedData(SortField.ECONOMY);
            statisticCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals("Ben Cutting", statisticCSV[0].player);
        } catch (StatisticsAnalyzerException statisticsAnalyzerException) {
            Assert.assertEquals(StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM, statisticsAnalyzerException.type);
        }
    }


    @Test
    public void givenIPLBowler_WhenSortedOn5wAnd4w_ShouldReturnSortedResult() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BOWLER, IPL_BOWLER_CSV_FILE_PATH);
            sortedData = iplAnalyzer.getStatWiseSortedData(SortField.FIVE_AND_FOUR_WICKET);
            statisticCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals("Lasith Malinga", statisticCSV[0].player);
        } catch (StatisticsAnalyzerException statisticsAnalyzerException) {
            Assert.assertEquals(StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM, statisticsAnalyzerException.type);
        }
    }

    @Test
    public void givenIPLBowler_WhenSortedOnAverage_ShouldReturnStrikeRate() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BOWLER, IPL_BOWLER_CSV_FILE_PATH);
            sortedData = iplAnalyzer.getStatWiseSortedData(SortField.AVG);
            statisticCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals("Krishnappa Gowtham", statisticCSV[0].player);
        } catch (StatisticsAnalyzerException statisticsAnalyzerException) {
            Assert.assertEquals(StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM, statisticsAnalyzerException.type);
        }
    }

    @Test
    public void givenIPLBowler_WhenSortedOnMaximumWicketWithBestAverage_ShouldReturnStrikeRate() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BOWLER, IPL_BOWLER_CSV_FILE_PATH);
            sortedData = iplAnalyzer.getStatWiseSortedData(SortField.WICKET);
            statisticCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals("Imran Tahir", statisticCSV[0].player);
        } catch (StatisticsAnalyzerException statisticsAnalyzerException) {
            Assert.assertEquals(StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM, statisticsAnalyzerException.type);
        }
    }

    @Test
    public void givenIPLBowler_WhenSortedOnBestBattingBowlingAverage_ShouldReturnSortedData() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BATSMAN, IPL_BATSMAN_CSV_FILE_PATH, IPL_BOWLER_CSV_FILE_PATH);
            sortedData = iplAnalyzer.getStatWiseSortedData(SortField.BEST_BATTING_BOWLING_AVERAGE);
            statisticCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals("Krishnappa Gowtham", statisticCSV[0].player);
        } catch (StatisticsAnalyzerException statisticsAnalyzerException) {
            Assert.assertEquals(StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM, statisticsAnalyzerException.type);
        }
    }

    @Test
    public void givenIPLBowler_WhenSortedOnAllRounder_ShouldReturnSortedData() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BATSMAN, IPL_BATSMAN_CSV_FILE_PATH, IPL_BOWLER_CSV_FILE_PATH);
            sortedData = iplAnalyzer.getStatWiseSortedData(SortField.ALL_ROUNDER);
            statisticCSV = new Gson().fromJson(sortedData, IPLDTO[].class);
            Assert.assertEquals("Hardik Pandya", statisticCSV[0].player);
        } catch (StatisticsAnalyzerException statisticsAnalyzerException) {
            Assert.assertEquals(StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM, statisticsAnalyzerException.type);
        }
    }

    @Test
    public void givenIPLBowler_WithWrongEnum_ShouldReturnException() {
        try {
            iplAnalyzer.loadIPLData(IPLAnalyzer.Statistics.BOWLER, IPL_BATSMAN_CSV_FILE_PATH);
        } catch (CSVBuilderException csvBuilderException) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ERROR_CAPTURING_CSV_HEADER, csvBuilderException.type);
        }
    }
}
