package com.decsaas.decmercado.service.application.core.exception;

public class ProductIdNotProvided extends RuntimeException {

    public ProductIdNotProvided() {
        super("Id do produto não informado!");
    }
}
