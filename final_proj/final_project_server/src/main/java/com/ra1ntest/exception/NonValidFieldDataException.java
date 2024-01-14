package com.ra1ntest.exception;

public class NonValidFieldDataException extends RuntimeException {
    public NonValidFieldDataException(String msg) {
        super(msg);
    }
}
