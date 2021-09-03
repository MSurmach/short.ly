package com.example.shortener.exception;

public class UserIsExistException extends RuntimeException {
    public UserIsExistException(String message) {
        super(message);
    }
}
