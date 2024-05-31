package com.decsaas.decmercado.service.adapters.out;

import com.decsaas.decmercado.service.adapters.in.controller.ProductMapper;
import com.decsaas.decmercado.service.adapters.out.repository.ProductEntity;
import com.decsaas.decmercado.service.adapters.out.repository.ProductRepository;
import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.ports.out.EditProductOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditProductAdapter implements EditProductOutputPort {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product edit(Product product) {
        ProductEntity productEntity = productRepository.save(productMapper.toProductEntity(product));
        return productMapper.toProduct(productEntity);
    }
}
