package com.mockproject.givetoget.utils;

public enum CodeMessage {
    SUCCESS("00", "Operation successful"),
    NO_DATA("01", "No data");

    private final String code;
    private final String message;

    CodeMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

