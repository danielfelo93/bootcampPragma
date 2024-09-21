package com.bootcamp.emazon.transaccion_micro.config.exceptionhandler;

import java.time.LocalDateTime;

import com.bootcamp.emazon.transaccion_micro.config.Constants;
import com.bootcamp.emazon.transaccion_micro.domain.exception.EmptyFieldException;
import com.bootcamp.emazon.transaccion_micro.domain.exception.InvalidDataException;
import com.bootcamp.emazon.transaccion_micro.domain.exception.StockUpdateException;
import com.bootcamp.emazon.transaccion_micro.driven.exceptions.ElementNotFoundException;
import com.bootcamp.emazon.transaccion_micro.driven.exceptions.NoDataFoundException;
import com.bootcamp.emazon.transaccion_micro.driven.exceptions.ProductAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                exception.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                Constants.DATOS_NO_ENCONTRADOS_EXCEPCION_MENSAJE, HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleProductAlreadyExistsException(ProductAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(exception.getMessage(),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleElementNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                Constants.ELEMENTO_NO_ENCONTRADO_EXCEPCION_MENSAJE, HttpStatus.CONFLICT.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidAgeException(InvalidDataException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                exception.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(StockUpdateException.class)
    public ResponseEntity<ExceptionResponse> handleStockUpdateException(StockUpdateException exception) {
        return  ResponseEntity.badRequest().body(new ExceptionResponse(
                exception.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
}