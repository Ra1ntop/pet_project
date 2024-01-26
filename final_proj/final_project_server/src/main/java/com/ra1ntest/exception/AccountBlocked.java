package com.ra1ntest.exception;

public class AccountBlocked extends RuntimeException {
    public AccountBlocked(String msg) {
        super(msg);
    }
}
