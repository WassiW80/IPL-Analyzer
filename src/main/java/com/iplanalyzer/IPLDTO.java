package com.iplanalyzer;

public class IPLDTO {
    public String player;
    public double average;
    public int match;
    public int numOfFour;
    public int numOfSix;
    public double strikeRate;
    public int runs;
    public double economy;

    public IPLDTO(IPLBatsmanCSV csvStat) {
        player = csvStat.player;
        average = csvStat.average;
        match = csvStat.match;
        numOfFour = csvStat.numOfFour;
        numOfSix = csvStat.numOfSix;
        strikeRate = csvStat.strikeRate;
        runs = csvStat.runs;
    }

    public IPLDTO(IPLBowlerCSV csvStat) {
        player = csvStat.player;
        average = csvStat.average;
        strikeRate = csvStat.strikeRate;
        economy = csvStat.economy;
    }
}
