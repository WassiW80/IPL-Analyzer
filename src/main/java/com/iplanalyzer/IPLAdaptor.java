package com.iplanalyzer;

import com.csvparser.CSVBuilderException;
import com.csvparser.CSVBuilderFactory;
import com.csvparser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IPLAdaptor {
    public abstract Map<String, IPLDTO> loadIPLData(String... csvFilePath);

    public static <E> Map<String, IPLDTO> loadIPLData(Class<E> iplClass, String csvFilePath) {
        Map<String, IPLDTO> statisticMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvIterator = icsvBuilder.getCSVFileIterator(reader, iplClass);
            Iterable<E> csvIterable = () -> csvIterator;
            if (iplClass.getName() == "com.iplanalyzer.IPLBatsmanCSV") {
                StreamSupport.stream(csvIterable.spliterator(), false).
                        map(IPLBatsmanCSV.class::cast).
                        forEach(csvStatistic -> statisticMap.put(csvStatistic.player, new IPLDTO(csvStatistic)));
            } else if (iplClass.getName() == "com.iplanalyzer.IPLBowlerCSV") {
                StreamSupport.stream(csvIterable.spliterator(), false).
                        map(IPLBowlerCSV.class::cast).
                        forEach(csvStatistic -> statisticMap.put(csvStatistic.player, new IPLDTO(csvStatistic)));
            }
            return statisticMap;
        } catch (IOException ioException) {
            throw new StatisticsAnalyzerException(ioException.getMessage(), StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM);
        } catch (RuntimeException runtimeException) {
            throw new CSVBuilderException(runtimeException.getMessage(), CSVBuilderException.ExceptionType.ERROR_CAPTURING_CSV_HEADER);
        }
    }
}
