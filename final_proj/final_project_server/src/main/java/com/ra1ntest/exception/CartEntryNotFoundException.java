package com.ra1ntest.exception;

public class CartEntryNotFoundException extends RuntimeException {
    public CartEntryNotFoundException(String msg) {
        super(msg);
    }
}
