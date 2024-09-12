package com.bootcamp.emazon.stock_micro.domain.exception;

public class LimitExceededException extends RuntimeException {
    public LimitExceededException(String message) {
        super(message);
    }
}
