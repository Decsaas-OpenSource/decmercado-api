package com.decsaas.decmercado.service.adapters.in.error;

import com.decsaas.decmercado.service.application.core.exception.ProductConflictException;
import com.decsaas.decmercado.service.application.core.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.Locale;
import java.util.Objects;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponse> handleThrowable(WebExchangeBindException ex, Locale locale) {
        ErrorResponse errorResponse = new ErrorResponse(
                String.format(" %s -> %s ", Objects.requireNonNull(ex.getFieldError()).getField(), ex.getFieldError().getDefaultMessage())
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductConflictException.class)
    public ResponseEntity<ErrorResponse> handleThrowable(ProductConflictException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleThrowable(ProductNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
