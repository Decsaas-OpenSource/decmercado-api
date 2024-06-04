package com.decsaas.decmercado.service.application.core.exception;

public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException() {
        super("Produto já existe na sua lista");
    }
}
