package com.decsaas.decmercado.service.application.ports.out;

import com.decsaas.decmercado.service.application.core.domain.Product;

import java.util.List;

public interface FindProductOutputPort {
    Product findById(String userId, String id);

    Product findByDescription(String userId, String description);

    List<Product> findAll(String userId);
}
