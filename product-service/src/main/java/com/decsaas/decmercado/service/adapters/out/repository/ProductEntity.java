package com.decsaas.decmercado.service.adapters.out.repository;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "produto")
public class ProductEntity {

    @NotBlank
    @Field("usuario_id")
    private String userId;

    @Id
    @NotBlank
    private String id;

    @NotBlank
    @Field("descricao")
    private String description;
}
