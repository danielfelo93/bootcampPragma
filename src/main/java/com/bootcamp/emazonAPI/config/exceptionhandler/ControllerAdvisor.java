package com.bootcamp.emazonAPI.config.exceptionhandler;

import java.time.LocalDateTime;

import com.bootcamp.emazonAPI.domain.exception.CharacterLimitExceededException;
import com.bootcamp.emazonAPI.domain.exception.EmptyFieldException;
import com.bootcamp.emazonAPI.domain.service.ConstantesDominio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bootcamp.emazonAPI.config.Constants;
import com.bootcamp.emazonAPI.driven.exceptions.ElementNotFoundException;
import com.bootcamp.emazonAPI.driven.exceptions.NoDataFoundException;
import com.bootcamp.emazonAPI.driven.exceptions.ProductAlreadyExistsException;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                exception.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(CharacterLimitExceededException.class)
    public ResponseEntity<ExceptionResponse> handleCharlimitException(CharacterLimitExceededException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                exception.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                Constants.DATOS_NO_ENCONTRADOS_EXCEPCION_MENSAJE, HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleProductAlreadyExistsException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(Constants.CATEGORIA_YA_EXISTE_EXCEPCION_MENSAJE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleElementNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                Constants.ELEMENTO_NO_ENCONTRADO_EXCEPCION_MENSAJE, HttpStatus.CONFLICT.toString(), LocalDateTime.now()));
    }

    //    /*@ExceptionHandler(NegativeNotAllowedException.class)
//    public ResponseEntity<ExceptionResponse> handleNegativeNotAllowedException(NegativeNotAllowedException exception) {
//        return ResponseEntity.badRequest().body(new ExceptionResponse(
//                String.format(Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE, exception.getMessage()),
//                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
//    }*/
}

