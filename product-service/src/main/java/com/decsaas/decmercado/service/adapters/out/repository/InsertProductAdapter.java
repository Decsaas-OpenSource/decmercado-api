package com.decsaas.decmercado.service.adapters.out.repository;

import com.decsaas.decmercado.service.adapters.in.controller.ProductMapper;
import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.ports.out.InsertProductOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertProductAdapter implements InsertProductOutputPort {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void insert(Product product) {
        productRepository.save(productMapper.toProductEntity(product));
    }
}
