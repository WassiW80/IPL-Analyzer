package com.iplanalyzer;

public class StatisticsAnalyzerException extends RuntimeException {
    public enum ExceptionType {NO_STATISTIC_DATA, INVALID_STATISTIC_TYPE, STATISTIC_FILE_PROBLEM}

    ExceptionType type;

    public StatisticsAnalyzerException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
