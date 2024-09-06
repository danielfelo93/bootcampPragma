package com.bootcamp.emazonapi.config.exceptionhandler;

import java.time.LocalDateTime;

import com.bootcamp.emazonapi.domain.exception.InvalidAgeException;
import com.bootcamp.emazonapi.domain.exception.LimitExceededException;
import com.bootcamp.emazonapi.domain.exception.EmptyFieldException;
import com.bootcamp.emazonapi.domain.service.ConstantesDominio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bootcamp.emazonapi.config.Constants;
import com.bootcamp.emazonapi.driven.exceptions.ElementNotFoundException;
import com.bootcamp.emazonapi.driven.exceptions.NoDataFoundException;
import com.bootcamp.emazonapi.driven.exceptions.ProductAlreadyExistsException;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                exception.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(LimitExceededException.class)
    public ResponseEntity<ExceptionResponse> handleCharlimitException(LimitExceededException exception) {
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                exception.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(InvalidAgeException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidAgeException(InvalidAgeException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                ConstantesDominio.FECHANACIMIENTO_EDAD_MENOR_MENSAJE, HttpStatus.CONFLICT.toString(), LocalDateTime.now()));
    }

}

