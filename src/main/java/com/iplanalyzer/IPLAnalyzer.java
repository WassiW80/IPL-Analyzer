package com.iplanalyzer;

import census.CSVBuilderFactory;
import census.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IPLAnalyzer {
    Map<SortField, Comparator<IPLDTO>> sortMap;
    Map<String, IPLDTO> statMap = new HashMap<>();
    List<IPLDTO> collect;

    public IPLAnalyzer() {
        this.sortMap = new HashMap<>();
        this.sortMap.put(SortField.AVG, Comparator.comparing(stat -> stat.average));
        this.sortMap.put(SortField.STRIKING_RATE, Comparator.comparing(stat -> stat.strikeRate));
    }

    public int loadIPLBatsmenData(String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {

            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();

            Iterator<IPLBatsmanCSV> csvIterator = icsvBuilder.getCSVFileIterator(reader, IPLBatsmanCSV.class);
            Iterable<IPLBatsmanCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false).
                    forEach(csvStat -> statMap.put(csvStat.player, new IPLDTO(csvStat)));
            return statMap.size();
        } catch (IOException e) {
            throw new StatAnalyzerException(e.getMessage(), StatAnalyzerException.ExceptionType.STAT_FILE_PROBLEM);
        }


    }

    public String getStatWiseSortedData(SortField sortField) {
        if (statMap == null || statMap.size() == 0) {
            throw new StatAnalyzerException("No Census Data",
                    StatAnalyzerException.ExceptionType.NO_STAT_DATA);
        }
        collect = statMap.values().stream().collect(Collectors.toList());
        this.sort(this.sortMap.get(sortField).reversed());
        String sortedStateCensusJson = new Gson().toJson(collect);
        return sortedStateCensusJson;
    }

    private void sort(Comparator<IPLDTO> comparator) {
        for (int i = 0; i < collect.size() - 1; i++) {
            for (int j = 0; j < collect.size() - 1 - i; j++) {
                IPLDTO census1 = collect.get(j);
                IPLDTO census2 = collect.get(j + 1);
                if (comparator.compare(census1, census2) > 0) {
                    collect.set(j, census2);
                    collect.set(j + 1, census1);
                }
            }
        }
    }
}
