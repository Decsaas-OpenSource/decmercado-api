package com.decsaas.decmercado.service.application.ports.out;

import com.decsaas.decmercado.service.application.core.domain.Product;

import java.util.List;

public interface FindProductOutputPort {
    Product findById(String id);

    Product findByDescription(String description);

    List<Product> findAll();
}
