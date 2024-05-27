package com.decsaas.decmercado.gateway.adapter.out.repository;

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
@Document(collection = "users")
public class UserEntity {

    @Id
    @NotBlank
    private String id;

    @NotBlank
    @Field("nome")
    private String name;

    @NotBlank
    @Field("senha")
    private String password;

    @NotBlank
    @Field("conta_expirada")
    private boolean accountExpired;

    @NotBlank
    @Field("conta_bloqueada")
    private boolean accountLocked;

    @NotBlank
    @Field("credenciais_expiradas")
    private boolean credentialsExpired;

    @NotBlank
    @Field("habilitado")
    private boolean enabled;
}
