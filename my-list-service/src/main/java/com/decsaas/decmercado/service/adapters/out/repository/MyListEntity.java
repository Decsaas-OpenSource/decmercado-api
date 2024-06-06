package com.decsaas.decmercado.service.adapters.out.repository;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity(name = "minha_lista")
public class MyListEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(name = "usuario_id", nullable = false)
    private final String userId;

    @Column(name = "produto_id", nullable = false)
    private final String productId;

    @Column(name = "produto_descricao", nullable = false)
    private final String productDescription;

    @Column(name = "comentario", nullable = false)
    private final String comment;

    @Column(name = "quantidade", nullable = false, precision = 12, scale = 3)
    private final BigDecimal amount;

    @Column(name = "no_carrinho")
    private final boolean selected;
}
