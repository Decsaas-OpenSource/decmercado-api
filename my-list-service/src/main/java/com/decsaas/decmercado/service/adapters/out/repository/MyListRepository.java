package com.decsaas.decmercado.service.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyListRepository extends JpaRepository<MyListEntity, Long> {

    boolean existsByUserIdAndProductIdAndSelected(String userId, String productId, boolean selected);
}
