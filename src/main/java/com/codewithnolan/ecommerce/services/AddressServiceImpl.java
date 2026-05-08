package com.codewithnolan.ecommerce.services;

import com.codewithnolan.ecommerce.entities.Address;
import com.codewithnolan.ecommerce.exception.EntityNotFoundException;
import com.codewithnolan.ecommerce.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public UUID create(Address address) {
        Address addressCreated = addressRepository.save(address);
        return addressCreated.getUUID();
    }

    @Override
    public Address getById(UUID id) {
        return addressRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Address Not Found with id: "+id)
        );
    }
}
