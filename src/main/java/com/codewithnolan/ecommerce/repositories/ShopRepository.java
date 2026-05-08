package com.codewithnolan.ecommerce.repositories;

import com.codewithnolan.ecommerce.entities.Shop;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShopRepository extends JpaRepository<Shop, UUID> {

    @NonNull
    @Query("SELECT s FROM shops s")
    List<Shop> findAll();

    @NonNull
    @Query("SELECT s FROM shops s WHERE s.id=:id")
    Optional<Shop> findById(@Param("id") @NonNull UUID id);
}
