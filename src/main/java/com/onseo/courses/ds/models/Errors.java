package com.onseo.courses.ds.models;

public class Errors {
    private int errorCode;
    private String errorMessage;

    public Errors(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
