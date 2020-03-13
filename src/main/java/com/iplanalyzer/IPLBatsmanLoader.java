package com.iplanalyzer;

import com.csvparser.CSVBuilderFactory;
import com.csvparser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IPLBatsmanLoader extends IPLAdaptor {

    @Override
    public Map<String, IPLDTO> loadIPLData(String... csvFilePath) {
        Map<String, IPLDTO> iplMap = super.loadIPLData(IPLBatsmanCSV.class, csvFilePath[0]);
        if (csvFilePath.length > 1)
            this.loadIPLData(iplMap, csvFilePath[1]);
        return iplMap;
    }

    private Map<String, IPLDTO> loadIPLData(Map<String, IPLDTO> ipldtoMap, String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLBowlerCSV> csvIterator = csvBuilder.getCSVFileIterator(reader, IPLBowlerCSV.class);
            Iterable<IPLBowlerCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false).filter(stat -> ipldtoMap.get(stat.player) != null)
                    .forEach(stat -> {
                        ipldtoMap.get(stat.player).bowlerAverage = stat.average;
                        ipldtoMap.get(stat.player).allWicket = stat.wicket;
                    });

            return ipldtoMap;
        } catch (IOException e) {
            throw new StatisticsAnalyzerException(e.getMessage(),
                    StatisticsAnalyzerException.ExceptionType.STATISTIC_FILE_PROBLEM);
        }
    }
}
