package com.decsaas.decmercado.service.adapters.out;

import com.decsaas.decmercado.service.adapters.in.controller.MyListMapper;
import com.decsaas.decmercado.service.adapters.out.repository.MyListEntity;
import com.decsaas.decmercado.service.adapters.out.repository.MyListRepository;
import com.decsaas.decmercado.service.application.core.domain.MyList;
import com.decsaas.decmercado.service.application.ports.out.InsertMyListOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertMyListAdapter implements InsertMyListOutputPort {

    @Autowired
    private MyListRepository myListRepository;

    @Autowired
    private MyListMapper myListMapper;

    @Override
    public MyList insert(MyList myList) {
        MyListEntity result = myListRepository.save(myListMapper.toMyListEntity(myList));
        return myListMapper.toMyList(result);
    }
}
