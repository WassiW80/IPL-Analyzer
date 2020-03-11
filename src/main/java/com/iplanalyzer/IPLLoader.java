package com.iplanalyzer;

import census.CSVBuilderFactory;
import census.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IPLLoader {

    public static <E> Map<String, IPLDTO> loadIPLData(IPLAnalyzer.Stat stat, String csvFilePath) {
        if (stat.equals(IPLAnalyzer.Stat.BATSMAN))
            return loadIPLData(IPLBatsmanCSV.class,csvFilePath);
        if (stat.equals(IPLAnalyzer.Stat.BOWLER))
            return loadIPLData(IPLBowlerCSV.class,csvFilePath);
        else
            throw new StatAnalyzerException("Invalid stat type", StatAnalyzerException.ExceptionType.INVALID_STAT_TYPE);
    }

        private static <E> Map<String, IPLDTO> loadIPLData(Class<E> iplClass, String csvFilePath) {
        Map<String, IPLDTO> statMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvIterator = icsvBuilder.getCSVFileIterator(reader, iplClass);
            Iterable<E> csvIterable = () -> csvIterator;
            if (iplClass.getName() == "com.iplanalyzer.IPLBatsmanCSV") {
                StreamSupport.stream(csvIterable.spliterator(), false).
                        map(IPLBatsmanCSV.class::cast).
                        forEach(csvStat -> statMap.put(csvStat.player, new IPLDTO(csvStat)));
            } else if (iplClass.getName() == "com.iplanalyzer.IPLBowlerCSV") {
                StreamSupport.stream(csvIterable.spliterator(), false).
                        map(IPLBowlerCSV.class::cast).
                        forEach(csvStat -> statMap.put(csvStat.player, new IPLDTO(csvStat)));
            }
            return statMap;
        } catch (IOException e) {
            throw new StatAnalyzerException(e.getMessage(), StatAnalyzerException.ExceptionType.STAT_FILE_PROBLEM);
        }
    }
}
