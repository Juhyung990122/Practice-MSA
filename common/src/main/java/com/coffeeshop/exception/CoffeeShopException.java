package com.coffeeshop.exception;

import org.springframework.http.HttpStatus;

public abstract class CoffeeShopException extends RuntimeException {

    private final HttpStatus httpStatus;

    public CoffeeShopException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
