package com.decsaas.decmercado.service.adapters.in.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequest {

    private String id;

    @NotBlank
    private String description;
}
