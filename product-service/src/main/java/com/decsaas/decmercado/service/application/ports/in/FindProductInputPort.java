package com.decsaas.decmercado.service.application.ports.in;

import com.decsaas.decmercado.service.application.core.domain.Product;

public interface FindProductInputPort {

    Product findById(String id);
}
