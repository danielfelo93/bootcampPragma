package com.bootcamp.emazonapi.domain.exception;

public class CharacterLimitExceededException extends RuntimeException {
    public CharacterLimitExceededException(String message) {
        super(message);
    }
}
