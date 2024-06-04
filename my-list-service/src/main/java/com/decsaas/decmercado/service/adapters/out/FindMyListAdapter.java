package com.decsaas.decmercado.service.adapters.out;

import com.decsaas.decmercado.service.adapters.out.repository.MyListRepository;
import com.decsaas.decmercado.service.application.ports.out.FindMyListOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindMyListAdapter implements FindMyListOutputPort {

    @Autowired
    MyListRepository myListRepository;

    @Override
    public boolean productAlreadyExists(String userId, String productId, boolean selected) {
        return myListRepository.existsByUserIdAndProductIdAndSelected(userId, productId, selected);
    }
}
