package com.decsaas.decmercado.service.adapters.out.repository;

import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.ports.out.InsertProductOutputPort;
import org.springframework.stereotype.Component;

@Component
public class InsertProductAdapter implements InsertProductOutputPort {

    //TODO: criar repository com mongo

    @Override
    public void insert(Product product) {
        System.out.println(product);
    }
}
