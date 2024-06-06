package com.decsaas.decmercado.service.adapters.in.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WebHookResquest {

    @NotBlank
    private final String userId;

    @NotBlank
    private final String productId;

    @NotBlank
    private final String productDescription;
}
