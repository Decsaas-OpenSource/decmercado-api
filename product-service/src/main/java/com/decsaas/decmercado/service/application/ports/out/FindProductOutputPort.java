package com.decsaas.decmercado.service.application.ports.out;

import com.decsaas.decmercado.service.application.core.domain.Product;

public interface FindProductOutputPort {
    Product findById(String id);

    Product findByDescription(String description);
}
