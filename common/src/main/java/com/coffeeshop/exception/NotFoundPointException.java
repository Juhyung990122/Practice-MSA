package com.coffeeshop.exception;

import org.springframework.http.HttpStatus;

public class NotFoundPointException extends CoffeeShopException {
    public NotFoundPointException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
