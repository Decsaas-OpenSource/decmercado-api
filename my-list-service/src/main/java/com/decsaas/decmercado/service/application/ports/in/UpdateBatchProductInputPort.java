package com.decsaas.decmercado.service.application.ports.in;

public interface UpdateBatchProductInputPort {
    void update(String userId, String productId, String productDescription);
}
