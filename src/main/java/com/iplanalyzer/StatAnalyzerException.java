package com.iplanalyzer;

public class StatAnalyzerException extends RuntimeException {
    public enum ExceptionType {NO_STAT_DATA, STAT_FILE_PROBLEM}

    ExceptionType type;

    public StatAnalyzerException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
