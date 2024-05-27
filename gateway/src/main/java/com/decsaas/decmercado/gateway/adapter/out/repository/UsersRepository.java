package com.decsaas.decmercado.gateway.adapter.out.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByName(String userName);
}
