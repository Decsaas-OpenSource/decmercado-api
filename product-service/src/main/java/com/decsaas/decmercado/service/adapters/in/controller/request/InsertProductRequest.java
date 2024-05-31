package com.decsaas.decmercado.service.adapters.in.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InsertProductRequest {

    @NotBlank
    @Schema(description = "Descrição do produto", example = "Carne")
    private String description;
}
