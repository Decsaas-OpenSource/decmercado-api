package com.decsaas.decmercado.service.adapters.in.controller;

import com.decsaas.decmercado.service.adapters.in.controller.request.InsertMyListRequest;
import com.decsaas.decmercado.service.adapters.in.controller.response.MyListResponse;
import com.decsaas.decmercado.service.adapters.out.repository.MyListEntity;
import com.decsaas.decmercado.service.application.core.domain.MyList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MyListMapper {

    @Mapping(target = "id", ignore = true)
    MyList toMyList(String userId, InsertMyListRequest insertMyListRequest);

    MyList toMyList(MyListEntity result);

    MyListResponse toProductResponse(MyList myList);

    MyListEntity toMyListEntity(MyList myList);
}
