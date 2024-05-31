package com.decsaas.decmercado.service.adapters.in.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EditProductRequest {

    @NotBlank
    @Schema(description = "Identificação do produto", example = "66562000f53d387761544839")
    private String id;

    @NotBlank
    @Schema(description = "Nova descrição para o produto", example = "Carne moida")
    private String description;
}
