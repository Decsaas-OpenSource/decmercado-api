package com.decsaas.decmercado.service.application.core.usecase;

import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.ports.out.FindProductOutputPort;

public class ExistsUseCase {

    private final FindProductOutputPort findProductOutputPort;

    public ExistsUseCase(FindProductOutputPort findProductOutputPort) {
        this.findProductOutputPort = findProductOutputPort;
    }

    public boolean isNotExists(Product product) {
        return findProductOutputPort.findById(product.getId()) == null;
    }

    public boolean isExistsByDescription(Product product) {
        Product productByDescription = findProductOutputPort.findByDescription(product.getDescription());

        if (productByDescription == null)
            return false;

        return !productByDescription.getId().equals(product.getId());
    }
}
