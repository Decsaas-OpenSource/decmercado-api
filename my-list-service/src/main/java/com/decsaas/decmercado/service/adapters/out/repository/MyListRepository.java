package com.decsaas.decmercado.service.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MyListRepository extends JpaRepository<MyListEntity, Long> {

    boolean existsByUserIdAndProductIdAndSelected(String userId, String productId, boolean selected);

    @Modifying
    @Query("update minha_lista m set m.productDescription = ?1 where m.userId = ?3 and m.productId = ?2")
    void updateProductionDescription(String productDescription,
                                     String productId,
                                     String userId);
}
