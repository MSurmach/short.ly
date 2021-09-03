package com.example.shortener.exception;

public class UserIsNotExistException extends RuntimeException {
    public UserIsNotExistException(String message) {
        super(message);
    }
}
