package com.decsaas.decmercado.service.application.core.usecase;

import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.core.exception.ProductConflictException;
import com.decsaas.decmercado.service.application.ports.in.InsertProductInputPort;
import com.decsaas.decmercado.service.application.ports.out.FindProductOutputPort;
import com.decsaas.decmercado.service.application.ports.out.InsertProductOutputPort;

public class InsertProductUseCase implements InsertProductInputPort {

    private final InsertProductOutputPort insertProductOutputPort;
    private final ExistsUseCase existsUseCase;

    public InsertProductUseCase(InsertProductOutputPort insertProductOutputPort,
                                FindProductOutputPort findProductOutputPort) {
        this.insertProductOutputPort = insertProductOutputPort;
        this.existsUseCase = new ExistsUseCase(findProductOutputPort);
    }

    @Override
    public Product insert(Product product) {
        if (existsUseCase.isExistsByDescription(product))
            throw new ProductConflictException();

        return insertProductOutputPort.insert(product);
    }

}
