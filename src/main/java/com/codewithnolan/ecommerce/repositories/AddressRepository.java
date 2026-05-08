package com.codewithnolan.ecommerce.repositories;

import com.codewithnolan.ecommerce.entities.Address;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    @NonNull
    @Query("SELECT a FROM addresses a")
    List<Address> findAll();

    @NonNull
    @Query("SELECT a FROM addresses a WHERE a.id=:id")
    Optional<Address> findById(@Param("id") @NonNull UUID id);
}
