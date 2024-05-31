package com.decsaas.decmercado.service.application.ports.in;

import com.decsaas.decmercado.service.application.core.domain.Product;

import java.util.List;

public interface FindProductInputPort {

    Product findById(String userId, String id);

    List<Product> findAll(String userId);
}
