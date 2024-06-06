package com.decsaas.decmercado.service.adapters.in.ampq;

import java.io.Serializable;

//TODO: Ideal é ter um projeto common, mas para não criar complexidade no exemplo, não será criado
public class Product implements Serializable {

    private String userId;
    private String id;
    private String description;

    public Product() {
    }

    public Product(String userId, String id, String description) {
        this.userId = userId;
        this.id = id;
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
