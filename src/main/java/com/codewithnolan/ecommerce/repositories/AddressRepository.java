package com.codewithnolan.ecommerce.repositories;

import com.codewithnolan.ecommerce.entities.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
}
