package com.decsaas.decmercado.service.application.core.domain;

public class Product {

    private final String userId;
    private final String id;
    private final String description;

    public Product(String userId, String id, String description) {
        this.userId = userId;
        this.id = id;
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
