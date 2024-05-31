package com.decsaas.decmercado.service.adapters.out.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

    Optional<ProductEntity> findByDescription(String description);
}
