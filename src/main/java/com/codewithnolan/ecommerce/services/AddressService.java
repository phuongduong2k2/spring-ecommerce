package com.codewithnolan.ecommerce.services;

import com.codewithnolan.ecommerce.entities.Address;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    List<Address> getAll();

    UUID create(Address address);

    Address getById(UUID id);
}
