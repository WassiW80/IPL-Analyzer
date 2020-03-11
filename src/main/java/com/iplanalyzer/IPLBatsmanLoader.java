package com.iplanalyzer;

import java.util.Map;

public class IPLBatsmanLoader extends IPLAdaptor {

    @Override
    public Map<String, IPLDTO> loadIPLData(String csvFilePath) {
        return super.loadIPLData(IPLBatsmanCSV.class, csvFilePath);
    }
}
