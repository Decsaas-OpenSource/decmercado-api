package com.decsaas.decmercado.service.adapters.out;

import com.decsaas.decmercado.service.adapters.in.controller.ProductMapper;
import com.decsaas.decmercado.service.adapters.out.repository.ProductRepository;
import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.core.exception.ProductNotFoundException;
import com.decsaas.decmercado.service.application.ports.out.FindProductOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindProductAdapter implements FindProductOutputPort {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product findById(String id) {
        return productRepository.findById(id)
                .map(productEntity -> productMapper.toProduct(productEntity))
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product findByDescription(String description) {
        return productRepository.findByDescription(description)
                .map(productEntity -> productMapper.toProduct(productEntity))
                .orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll()
                .stream().map(productEntity -> productMapper.toProduct(productEntity))
                .toList();
    }

}
