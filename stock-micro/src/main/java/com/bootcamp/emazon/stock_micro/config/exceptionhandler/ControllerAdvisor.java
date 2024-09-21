package com.bootcamp.emazon.stock_micro.config.exceptionhandler;

import java.time.LocalDateTime;

import com.bootcamp.emazon.stock_micro.config.Constants;
import com.bootcamp.emazon.stock_micro.domain.exception.DuplicatedFieldException;
import com.bootcamp.emazon.stock_micro.domain.exception.EmptyFieldException;
import com.bootcamp.emazon.stock_micro.domain.exception.LimitExceededException;
import com.bootcamp.emazon.stock_micro.domain.service.ConstantesDominio;
import com.bootcamp.emazon.stock_micro.driven.exceptions.ElementNotFoundException;
import com.bootcamp.emazon.stock_micro.driven.exceptions.NoDataFoundException;
import com.bootcamp.emazon.stock_micro.driven.exceptions.ProductAlreadyExistsException;
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

    @ExceptionHandler(DuplicatedFieldException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicatedFieldException(DuplicatedFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                ConstantesDominio.CAMPO_CATEGORIA_DUPLICADA_MENSAJE, HttpStatus.CONFLICT.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                ConstantesDominio.CATEGORIA_NULA_MENSAJE, HttpStatus.CONFLICT.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                ConstantesDominio.CAMPO_PAGINA_NEGATIVE_MENSAJE, HttpStatus.CONFLICT.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse(
                "Ocurrió un error inesperado", HttpStatus.INTERNAL_SERVER_ERROR.toString(), LocalDateTime.now()));
    }
}