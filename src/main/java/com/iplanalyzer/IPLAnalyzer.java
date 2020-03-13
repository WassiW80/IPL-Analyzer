package com.iplanalyzer;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IPLAnalyzer {
    public enum Statistics {
        BOWLER, BATSMAN
    }

    Map<String, IPLDTO> statisticMap = new HashMap<>();
    List<IPLDTO> collect;

    public IPLAnalyzer() {
        SortField.initializeSortedMap();
    }

    public int loadIPLData(Statistics statistics, String... csvFilePath) {
        statisticMap = new IPLAdaptorFactory().getIPLAdaptor(statistics, csvFilePath);
        return statisticMap.size();
    }

    public String getStatWiseSortedData(SortField sortField) {
        collect = statisticMap.values().stream().collect(Collectors.toList());
        if (collect == null || collect.size() == 0) {
            throw new StatisticsAnalyzerException("No Census Data",
                    StatisticsAnalyzerException.ExceptionType.NO_STATISTIC_DATA);
        }
        this.sort(sortField.sortMap.get(sortField).reversed());
        String sortedStatisticsJson = new Gson().toJson(collect);
        return sortedStatisticsJson;
    }

    private void sort(Comparator<IPLDTO> comparator) {
        for (int i = 0; i < collect.size() - 1; i++) {
            for (int j = 0; j < collect.size() - 1 - i; j++) {
                IPLDTO statistics1 = collect.get(j);
                IPLDTO statistics2 = collect.get(j + 1);
                if (comparator.compare(statistics1, statistics2) > 0) {
                    collect.set(j, statistics2);
                    collect.set(j + 1, statistics1);
                }
            }
        }
    }
}
