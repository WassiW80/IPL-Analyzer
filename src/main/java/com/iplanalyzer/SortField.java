package com.iplanalyzer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public enum SortField {
    STRIKING_RATE,
    FOUR,
    SIX,
    FOUR_AND_SIX,
    ECONOMY,
    FIVE_AND_FOUR_WICKET,
    WICKET,
    BEST_BATTING_BOWLING_AVERAGE,
    RUN,
    ALL_ROUNDER,
    AVG;

    static Map<SortField, Comparator<IPLDTO>> sortMap=new HashMap<>();
    static void initializeSortedMap(){
        sortMap.put(SortField.AVG, Comparator.comparing(statistic -> statistic.average));
        sortMap.put(SortField.STRIKING_RATE, Comparator.comparing(statistic -> statistic.strikeRate));
        sortMap.put(SortField.FOUR, Comparator.comparing(statistic -> statistic.numOfFour));
        sortMap.put(SortField.SIX, Comparator.comparing(statistic -> statistic.numOfSix));
        sortMap.put(SortField.FOUR_AND_SIX, Comparator.comparing(statistic -> (statistic.numOfSix + statistic.numOfFour)));
        sortMap.put(SortField.ECONOMY, Comparator.comparing(statistic -> statistic.economy));
        sortMap.put(SortField.FIVE_AND_FOUR_WICKET, Comparator.comparing(statistic -> (statistic.fiveWicket + statistic.fourWicket)));
        sortMap.put(SortField.WICKET, Comparator.comparing(statistic -> statistic.wicket));
        sortMap.put(SortField.BEST_BATTING_BOWLING_AVERAGE, new CompareAverage());
        sortMap.put(SortField.ALL_ROUNDER, new CompareAllRounder());
        sortMap.put(SortField.RUN, Comparator.comparing(statistic -> statistic.runs));

    }
}
