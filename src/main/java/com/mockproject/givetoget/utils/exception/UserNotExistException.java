package com.mockproject.givetoget.utils.exception;

public class UserNotExistException extends RuntimeException{

    public UserNotExistException(String message) {
        super(message);
    }
}
