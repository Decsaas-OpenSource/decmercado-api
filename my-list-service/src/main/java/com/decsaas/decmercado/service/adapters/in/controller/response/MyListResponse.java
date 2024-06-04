package com.decsaas.decmercado.service.adapters.in.controller.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MyListResponse {

    private final Long id;
    private final String productId;
    private final String productDescription;
    private final String comment;
    private final BigDecimal amount;
}
