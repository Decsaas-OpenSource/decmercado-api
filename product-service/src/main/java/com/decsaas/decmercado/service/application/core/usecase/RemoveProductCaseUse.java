package com.decsaas.decmercado.service.application.core.usecase;

import com.decsaas.decmercado.service.application.core.exception.ProductNotFoundException;
import com.decsaas.decmercado.service.application.ports.in.RemoveProductInputPort;
import com.decsaas.decmercado.service.application.ports.out.FindProductOutputPort;
import com.decsaas.decmercado.service.application.ports.out.RemoveProductOutputPort;

public class RemoveProductCaseUse implements RemoveProductInputPort {

    private final RemoveProductOutputPort removeProductOutputPort;
    private final ExistsUseCase existsUseCase;

    public RemoveProductCaseUse(RemoveProductOutputPort removeProductOutputPort,
                                FindProductOutputPort findProductOutputPort) {
        this.removeProductOutputPort = removeProductOutputPort;
        this.existsUseCase = new ExistsUseCase(findProductOutputPort);
    }

    @Override
    public void remove(String id) {
        if (existsUseCase.isNotExists(id))
            throw new ProductNotFoundException();

        removeProductOutputPort.remove(id);
    }
}
