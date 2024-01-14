package com.ra1ntest.exception;

public class EntityUnexistsException extends RuntimeException {

    public EntityUnexistsException(String msg) {
        super(msg);
    }
}
