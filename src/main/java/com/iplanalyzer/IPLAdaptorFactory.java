package com.iplanalyzer;

import java.util.Map;

public class IPLAdaptorFactory {

    public <E> Map<String, IPLDTO> getIPLAdaptor(IPLAnalyzer.Stat stat, String csvFilePath) {
        if (stat.equals(IPLAnalyzer.Stat.BATSMAN))
            return new IPLBatsmanLoader().loadIPLData(csvFilePath);
        if (stat.equals(IPLAnalyzer.Stat.BOWLER))
            return new IPLBowlerLoader().loadIPLData(csvFilePath);
        else
            throw new StatAnalyzerException("Invalid stat type", StatAnalyzerException.ExceptionType.INVALID_STAT_TYPE);
    }
}
