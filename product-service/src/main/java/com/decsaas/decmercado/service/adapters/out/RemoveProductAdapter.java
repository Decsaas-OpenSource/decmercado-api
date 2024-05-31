package com.decsaas.decmercado.service.adapters.out;

import com.decsaas.decmercado.service.adapters.out.repository.ProductRepository;
import com.decsaas.decmercado.service.application.ports.out.RemoveProductOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveProductAdapter implements RemoveProductOutputPort {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void remove(String id) {
        productRepository.deleteById(id);
    }
}
