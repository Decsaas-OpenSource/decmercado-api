package com.decsaas.decmercado.service.application.core.domain;

import java.math.BigDecimal;

public class MyList {

    private final Long id;
    private final String userId;
    private final String productId;
    private final String productDescription;
    private final String comment;
    private final BigDecimal amount;

    public MyList(Long id,
                  String userId,
                  String productId,
                  String productDescription,
                  String comment,
                  BigDecimal amount) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.productDescription = productDescription;
        this.comment = comment;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getComment() {
        return comment;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
