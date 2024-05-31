package com.decsaas.decmercado.service.application.core.usecase;

import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.core.exception.ProductConflictException;
import com.decsaas.decmercado.service.application.core.exception.ProductNotFoundException;
import com.decsaas.decmercado.service.application.core.usecase.common.ExistsUseCase;
import com.decsaas.decmercado.service.application.ports.in.EditProductInputPort;
import com.decsaas.decmercado.service.application.ports.out.EditProductOutputPort;
import com.decsaas.decmercado.service.application.ports.out.FindProductOutputPort;

public class EditProductUseCase implements EditProductInputPort {

    private final EditProductOutputPort editProductOutputPort;
    private final ExistsUseCase existsUseCase;

    public EditProductUseCase(EditProductOutputPort editProductOutputPort,
                              FindProductOutputPort findProductOutputPort) {
        this.editProductOutputPort = editProductOutputPort;
        this.existsUseCase = new ExistsUseCase(findProductOutputPort);
    }

    @Override
    public Product edit(Product product) {
        if (existsUseCase.isNotExists(product))
            throw new ProductNotFoundException();

        if (existsUseCase.isExistsByDescription(product))
            throw new ProductConflictException();

        return editProductOutputPort.edit(product);
    }

}
