package com.iplanalyzer;

public class IPLDTO {
    public String player;
    public int match;
    public int numOfFour;
    public int numOfSix;
    public int runs;
    public int fiveWicket;
    public int fourWicket;
    public double strikeRate;
    public double average;
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
        fiveWicket = csvStat.fiveWicket;
        fourWicket = csvStat.fourWicket;
    }
}
