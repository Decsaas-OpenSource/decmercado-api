package com.decsaas.decmercado.service.adapters.in.controller;

import com.decsaas.decmercado.service.application.core.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product toProduct(ProductRequest productRequest);
}
