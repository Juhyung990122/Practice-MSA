package com.yeol.market.common.exception;

import org.springframework.http.HttpStatus;

public class NotEnoughBalanceException extends CoffeeShopException {
    public NotEnoughBalanceException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
