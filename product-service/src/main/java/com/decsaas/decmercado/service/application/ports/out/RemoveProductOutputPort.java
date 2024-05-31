package com.decsaas.decmercado.service.application.ports.out;

public interface RemoveProductOutputPort {
    void remove(String userId, String id);
}
