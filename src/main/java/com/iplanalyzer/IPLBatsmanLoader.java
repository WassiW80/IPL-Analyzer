package com.iplanalyzer;

import census.CSVBuilderFactory;
import census.ICSVBuilder;

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

    private Map<String, IPLDTO> loadIPLData(Map<String, IPLDTO> censusCSVMap, String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLBowlerCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IPLBowlerCSV.class);
            Iterable<IPLBowlerCSV> csvIterable = () -> censusCSVIterator;
            StreamSupport.stream(csvIterable.spliterator(), false).filter(stat -> censusCSVMap.get(stat.player) != null)
                    .forEach(stat -> {
                        censusCSVMap.get(stat.player).bowlerAverage = stat.average;
                        censusCSVMap.get(stat.player).allWicket=stat.wicket;
                    });

            return censusCSVMap;
        } catch (IOException e) {
            throw new StatAnalyzerException(e.getMessage(),
                    StatAnalyzerException.ExceptionType.STAT_FILE_PROBLEM);
        }
    }
}
