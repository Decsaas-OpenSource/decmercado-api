package com.decsaas.decmercado.service.adapters.in.controller;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductRequest {

    private String id;

    @NotBlank
    private String description;
}
