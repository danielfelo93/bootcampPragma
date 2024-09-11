package com.bootcamp.emazonapi.domain.exception;

public class DuplicatedFieldException extends RuntimeException {
    public DuplicatedFieldException(String message) {
        super(message);
    }
}
