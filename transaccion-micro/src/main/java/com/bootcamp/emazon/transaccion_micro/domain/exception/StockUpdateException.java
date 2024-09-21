package com.bootcamp.emazon.transaccion_micro.domain.exception;

public class StockUpdateException extends RuntimeException {
    public StockUpdateException(String message) {
        super(message);
    }
}
