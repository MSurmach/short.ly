package com.example.shortener.exception;

public class AuthorityIsNotExisted extends RuntimeException {
    public AuthorityIsNotExisted(String message) {
        super(message);
    }
}
