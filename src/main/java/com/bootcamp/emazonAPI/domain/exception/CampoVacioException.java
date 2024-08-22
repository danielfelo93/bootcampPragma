package com.bootcamp.emazonAPI.domain.exception;

public class CampoVacioException extends RuntimeException {
    public CampoVacioException(String mensaje) {
        super(mensaje);
    }
}

