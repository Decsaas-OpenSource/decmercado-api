package com.decsaas.decmercado.service.adapters.out;

import com.decsaas.decmercado.service.adapters.out.repository.MyListRepository;
import com.decsaas.decmercado.service.application.ports.out.UpdateBatchProductOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class UpdateBatchProductAdapter implements UpdateBatchProductOutputPort {

    @Autowired
    private MyListRepository myListRepository;

    @Override
    public void update(String userId, String productId, String productDescription) {
        myListRepository.updateProductionDescription(productDescription,
                productId,
                userId);
    }
}
