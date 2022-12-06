package com.yeol.market.common.exception;

import org.springframework.http.HttpStatus;

public class NotFoundProductExcepton extends CoffeeShopException {
    public NotFoundProductExcepton(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
