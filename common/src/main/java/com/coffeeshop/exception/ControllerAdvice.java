package com.coffeeshop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CoffeeShopException.class)
    public ResponseEntity<String> handleJwtException(final CoffeeShopException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleUnhandledException(final RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body("Unhandled Exception");
    }
}
