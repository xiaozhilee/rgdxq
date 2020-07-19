package com.example.demo.testexample;

public class TestModel1 {
    private String errorCode;
    private String message;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TestModel1(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
