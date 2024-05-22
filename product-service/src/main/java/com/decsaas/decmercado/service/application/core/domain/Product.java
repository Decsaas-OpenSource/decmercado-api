package com.decsaas.decmercado.service.application.core.domain;

public class Product {

    private final String id;
    private final String description;

    public Product(String description, String id) {
        this.description = description;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
