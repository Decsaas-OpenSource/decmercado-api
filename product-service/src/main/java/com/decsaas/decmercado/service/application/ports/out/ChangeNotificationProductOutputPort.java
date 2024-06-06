package com.decsaas.decmercado.service.application.ports.out;

import com.decsaas.decmercado.service.application.core.domain.Product;

public interface ChangeNotificationProductOutputPort {
    void sendChange(Product product);

    void update(Product product);
}
