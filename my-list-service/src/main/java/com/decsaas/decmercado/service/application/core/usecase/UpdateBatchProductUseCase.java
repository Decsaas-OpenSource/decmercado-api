package com.decsaas.decmercado.service.application.core.usecase;

import com.decsaas.decmercado.service.application.ports.in.UpdateBatchProductInputPort;
import com.decsaas.decmercado.service.application.ports.out.UpdateBatchProductOutputPort;

public class UpdateBatchProductUseCase implements UpdateBatchProductInputPort {

    private final UpdateBatchProductOutputPort updateBatchProductOutputPort;

    public UpdateBatchProductUseCase(UpdateBatchProductOutputPort updateBatchProductOutputPort) {
        this.updateBatchProductOutputPort = updateBatchProductOutputPort;
    }

    @Override
    public void update(String userId, String productId, String productDescription) {
        updateBatchProductOutputPort.update(userId, productId, productDescription);
    }
}
