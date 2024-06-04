package com.decsaas.decmercado.service.application.ports.out;

import com.decsaas.decmercado.service.application.core.domain.MyList;

public interface InsertMyListOutputPort {
    MyList insert(MyList myList);
}
