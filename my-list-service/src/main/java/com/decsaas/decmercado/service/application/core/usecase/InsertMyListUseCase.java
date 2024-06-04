package com.decsaas.decmercado.service.application.core.usecase;

import com.decsaas.decmercado.service.application.core.domain.MyList;
import com.decsaas.decmercado.service.application.core.exception.ProductAlreadyExistsException;
import com.decsaas.decmercado.service.application.ports.in.InsertMyListInputPort;
import com.decsaas.decmercado.service.application.ports.out.FindMyListOutputPort;
import com.decsaas.decmercado.service.application.ports.out.InsertMyListOutputPort;

public class InsertMyListUseCase implements InsertMyListInputPort {

    private final InsertMyListOutputPort insertMyListOutputPort;
    private final FindMyListOutputPort findMyListOutputPort;

    public InsertMyListUseCase(InsertMyListOutputPort insertMyListOutputPort,
                               FindMyListOutputPort findMyListOutputPort) {
        this.insertMyListOutputPort = insertMyListOutputPort;
        this.findMyListOutputPort = findMyListOutputPort;
    }

    @Override
    public MyList insert(MyList myList) {
        if (findMyListOutputPort.productAlreadyExists(
                myList.getUserId(),
                myList.getProductId(),
                myList.isSelected())
        )
            throw new ProductAlreadyExistsException();

        return insertMyListOutputPort.insert(myList);
    }
}
