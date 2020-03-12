package com.iplanalyzer;

import java.util.Comparator;

public class CompareAverage implements Comparator<IPLDTO> {
    @Override
    public int compare(IPLDTO p1, IPLDTO p2) {
        int avg = (int) ((p1.battingAverage + p1.bowlerAverage) - (p2.battingAverage + p2.bowlerAverage));
        return avg;
    }
}
