package com.iplanalyzer;

import java.util.Map;

public class IPLBowlerLoader extends IPLAdaptor {

    @Override
    public Map<String, IPLDTO> loadIPLData(String csvFilePath) {
        return super.loadIPLData(IPLBowlerCSV.class, csvFilePath);
    }
}
