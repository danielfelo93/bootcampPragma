package com.bootcamp.emazonAPI.domain.exception;

public class CharacterLimitExceededException extends RuntimeException {
    public CharacterLimitExceededException(String message) {
        super(message);
    }
}
