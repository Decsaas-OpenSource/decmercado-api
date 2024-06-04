package com.decsaas.decmercado.service.application.ports.out;

public interface FindMyListOutputPort {

    boolean productAlreadyExists(String userId, String productId, boolean selected);
}
