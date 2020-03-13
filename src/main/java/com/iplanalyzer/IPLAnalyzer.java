package com.iplanalyzer;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IPLAnalyzer {
    public enum Stat {
        BOWLER, BATSMAN
    }

    Map<SortField, Comparator<IPLDTO>> sortMap;
    Map<String, IPLDTO> statMap = new HashMap<>();
    List<IPLDTO> collect;

    public IPLAnalyzer() {
        this.sortMap = new HashMap<>();
        this.sortMap.put(SortField.AVG, Comparator.comparing(stat -> stat.average));
        this.sortMap.put(SortField.STRIKING_RATE, Comparator.comparing(stat -> stat.strikeRate));
        this.sortMap.put(SortField.FOUR, Comparator.comparing(stat -> stat.numOfFour));
        this.sortMap.put(SortField.SIX, Comparator.comparing(stat -> stat.numOfSix));
        this.sortMap.put(SortField.FOUR_AND_SIX, Comparator.comparing(stat -> (stat.numOfSix + stat.numOfFour)));
        this.sortMap.put(SortField.ECONOMY, Comparator.comparing(stat -> stat.economy));
        this.sortMap.put(SortField.FIVE_AND_FOUR_WICKET, Comparator.comparing(stat -> (stat.fiveWicket + stat.fourWicket)));
        this.sortMap.put(SortField.WICKET, Comparator.comparing(stat -> stat.wicket));
        this.sortMap.put(SortField.BEST_BATTING_BOWLING_AVERAGE, new CompareAverage());
        this.sortMap.put(SortField.ALL_ROUNDER, new CompareAllRounder());
        this.sortMap.put(SortField.RUN, Comparator.comparing(stat -> stat.runs));

    }

    public int loadIPLData(Stat stat, String... csvFilePath) {
        statMap = new IPLAdaptorFactory().getIPLAdaptor(stat, csvFilePath);
        return statMap.size();
    }

    public String getStatWiseSortedData(SortField sortField) {
        collect = statMap.values().stream().collect(Collectors.toList());
        if (collect == null || collect.size() == 0) {
            throw new StatAnalyzerException("No Census Data",
                    StatAnalyzerException.ExceptionType.NO_STAT_DATA);
        }
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
