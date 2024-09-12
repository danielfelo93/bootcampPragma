package com.bootcamp.emazon.stock_micro.domain.exception;

public class DuplicatedFieldException extends RuntimeException {
    public DuplicatedFieldException(String message) {
        super(message);
    }
}
