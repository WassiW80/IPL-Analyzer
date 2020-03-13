package com.iplanalyzer;

import java.util.Comparator;

public class CompareAllRounder implements Comparator<IPLDTO> {
    @Override
    public int compare(IPLDTO p1, IPLDTO p2) {
        int total = (p1.runs*p1.allWicket)-(p2.runs*p2.allWicket);
        return total;
    }
}
