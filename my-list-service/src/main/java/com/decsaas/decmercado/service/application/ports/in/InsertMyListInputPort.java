package com.decsaas.decmercado.service.application.ports.in;

import com.decsaas.decmercado.service.application.core.domain.MyList;

public interface InsertMyListInputPort {

    MyList insert(MyList myList);
}
