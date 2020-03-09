package com.iplanalyzer;

public class IPLDTO {
    public String player;
    public double average;
    public int match;
    public int numOfFour;
    public int numOfSix;
    public double strikeRate;

    public IPLDTO(IPLBatsmanCSV csvStat) {
        player = csvStat.player;
        average = csvStat.average;
        match = csvStat.match;
        numOfFour = csvStat.numOfFour;
        numOfSix = csvStat.numOfSix;
        strikeRate = csvStat.strikeRate;
    }
}
