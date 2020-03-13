package com.iplanalyzer;

import java.util.Map;

public class IPLAdaptorFactory {

    public <E> Map<String, IPLDTO> getIPLAdaptor(IPLAnalyzer.Statistics statistics, String... csvFilePath) {
        if (statistics.equals(IPLAnalyzer.Statistics.BATSMAN))
            return new IPLBatsmanLoader().loadIPLData(csvFilePath);
        if (statistics.equals(IPLAnalyzer.Statistics.BOWLER))
            return new IPLBowlerLoader().loadIPLData(csvFilePath);
        throw new StatisticsAnalyzerException("Invalid stat type", StatisticsAnalyzerException.ExceptionType.INVALID_STATISTIC_TYPE);
    }
}
