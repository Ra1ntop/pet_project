package com.ra1ntest.api.controller;

import com.ra1ntest.exception.*;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = AccountBlocked.class)
    public ResponseEntity<String> globalHandle(AccountBlocked exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(value = CartEntryNotFoundException.class)
    public ResponseEntity<String> cartEntryNotFoundException(CartEntryNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(value = EntityUnexistsException.class)
    public ResponseEntity<String> entityUnexistsException(EntityUnexistsException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(value = EntityUnexistsName.class)
    public ResponseEntity<String> entityUnexistsName(EntityUnexistsName exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(value = NonValidFieldDataException.class)
    public ResponseEntity<String> nonValidFieldDataException(NonValidFieldDataException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(value = MalformedJwtException.class)
    public ResponseEntity<String> malformedJwtException(MalformedJwtException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
