package com.decsaas.decmercado.service.application.core.usecase;

import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.core.exception.ProductIdNotProvided;
import com.decsaas.decmercado.service.application.ports.in.FindProductInputPort;
import com.decsaas.decmercado.service.application.ports.out.FindProductOutputPort;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class FindProductUseCase implements FindProductInputPort {

    private final FindProductOutputPort findProductOutputPort;

    public FindProductUseCase(FindProductOutputPort findProductOutputPort) {
        this.findProductOutputPort = findProductOutputPort;
    }

    @Override
    public Product findById(String userId, String id) {
        if (ObjectUtils.isEmpty(id))
            throw new ProductIdNotProvided();

        return findProductOutputPort.findById(userId, id);
    }

    @Override
    public List<Product> findAll(String userId) {
        return findProductOutputPort.findAll(userId);
    }
}
