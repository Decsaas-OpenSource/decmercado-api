package com.decsaas.decmercado.service.application.core.usecase;

import com.decsaas.decmercado.service.application.core.domain.MyList;
import com.decsaas.decmercado.service.application.ports.in.InsertMyListInputPort;
import com.decsaas.decmercado.service.application.ports.out.InsertMyListOutputPort;

public class InsertMyListUseCase implements InsertMyListInputPort {

    private final InsertMyListOutputPort insertMyListOutputPort;

    public InsertMyListUseCase(InsertMyListOutputPort insertMyListOutputPort) {
        this.insertMyListOutputPort = insertMyListOutputPort;
    }

    @Override
    public MyList insert(MyList myList) {
        return insertMyListOutputPort.insert(myList);
    }
}
