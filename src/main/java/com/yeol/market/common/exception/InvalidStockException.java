package com.yeol.market.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidStockException extends CoffeeShopException{
    public InvalidStockException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
