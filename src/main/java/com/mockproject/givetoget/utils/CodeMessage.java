package com.mockproject.givetoget.utils;

public enum CodeMessage {
    SUCCESS("00", "Operation successful"),
    NO_DATA("01", "No data"),
    UNAUTHENTICATED("02", "Unauthenticated"),
    NO_ADDRESS("03", "No address"),
    USER_NOT_FOUND("04", "User not found"),
    REQUEST_NOT_FOUND("05", "User not found"),
    COMMENT_ERROR("06", "Comment error"),
    TRANSACTION_NOT_FOUND("07", "Transaction not found"),
    INVALID_REQUEST("0", "Invalid request");

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

