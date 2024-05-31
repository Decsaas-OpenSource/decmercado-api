package com.decsaas.decmercado.service.application.core.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super("Produto não encontrado!");
    }
}
