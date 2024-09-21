package com.bootcamp.emazon.stock_micro.domain.exception;

public class NoSuchElementException extends RuntimeException {
    public NoSuchElementException(String message) {
        super(message);
    }
}
