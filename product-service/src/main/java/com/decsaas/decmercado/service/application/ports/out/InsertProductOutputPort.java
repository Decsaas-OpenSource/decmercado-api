package com.decsaas.decmercado.service.application.ports.out;

import com.decsaas.decmercado.service.application.core.domain.Product;

public interface InsertProductOutputPort {
    Product insert(Product product);
}
