package com.decsaas.decmercado.service.application.core.usecase.common;

import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.ports.out.FindProductOutputPort;

public class ExistsUseCase {

    private final FindProductOutputPort findProductOutputPort;

    public ExistsUseCase(FindProductOutputPort findProductOutputPort) {
        this.findProductOutputPort = findProductOutputPort;
    }

    public boolean isNotExists(String userId, String id) {
        return findProductOutputPort.findById(userId, id) == null;
    }

    public boolean isNotExists(Product product) {
        return isNotExists(product.getUserId(), product.getId());
    }

    public boolean isExistsByDescription(Product product) {
        Product productByDescription = findProductOutputPort.findByDescription(
                product.getUserId(),
                product.getDescription());

        if (productByDescription == null)
            return false;

        return !productByDescription.getId().equals(product.getId());
    }
}
