package com.decsaas.decmercado.service.application.ports.out;

public interface UpdateBatchProductOutputPort {

    void update(String userId, String productId, String productDescription);
}
