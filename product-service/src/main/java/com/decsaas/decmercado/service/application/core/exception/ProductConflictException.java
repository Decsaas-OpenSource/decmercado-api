package com.decsaas.decmercado.service.application.core.exception;

public class ProductConflictException extends RuntimeException {

    public ProductConflictException() {
        super("Já existe um produto com a mesma descrição!");
    }
}
