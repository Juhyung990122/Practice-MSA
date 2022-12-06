package com.yeol.market.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidPriceException extends CoffeeShopException {

    public InvalidPriceException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
