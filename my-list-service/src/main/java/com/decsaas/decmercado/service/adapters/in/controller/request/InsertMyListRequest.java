package com.decsaas.decmercado.service.adapters.in.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InsertMyListRequest {

    @NotBlank
    private final String productId;

    @NotBlank
    private final String productDescription;

    private final String comment;

    @NotBlank
    private final String amount;

    private final boolean selected;
}
