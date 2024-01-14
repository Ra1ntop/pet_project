package com.ra1ntest.exception;

public class EntityUnexistsName extends RuntimeException {
    public EntityUnexistsName(String msg) {
        super(msg);
    }
}
