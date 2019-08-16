package com.allqj.virtual_number_administrate.util.resultProxy.exception;

public class ResultException extends RuntimeException {
    private int statusCode;
    private String exceptionMessage;

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }

    public ResultException(int statusCode, String exceptionMessage) {
        this.statusCode = statusCode;
        this.exceptionMessage = exceptionMessage;
    }
}
