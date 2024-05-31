package com.decsaas.decmercado.service.adapters.in.controller;

import com.decsaas.decmercado.service.adapters.in.controller.request.EditProductRequest;
import com.decsaas.decmercado.service.adapters.in.controller.request.InsertProductRequest;
import com.decsaas.decmercado.service.adapters.in.controller.response.ProductResponse;
import com.decsaas.decmercado.service.adapters.out.repository.ProductEntity;
import com.decsaas.decmercado.service.application.core.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product toProduct(String userId, InsertProductRequest insertProductRequest);

    Product toProduct(String userId, EditProductRequest editProductRequest);

    Product toProduct(ProductEntity productEntity);

    ProductEntity toProductEntity(Product product);

    ProductResponse toProductResponse(Product product);
}
