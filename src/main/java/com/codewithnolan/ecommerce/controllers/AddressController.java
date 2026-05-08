package com.codewithnolan.ecommerce.controllers;

import com.codewithnolan.ecommerce.entities.Address;
import com.codewithnolan.ecommerce.services.AddressService;
import com.codewithnolan.ecommerce.utils.ErrorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/public")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private ErrorMapper errorMapper;

    @GetMapping("/addresses")
    public List<Address> getAll() {
        return addressService.getAll();
    }

    @PostMapping("/addresses")
    public ResponseEntity<String> create(@RequestBody Address address) {
        addressService.create(address);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity getById(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(this.addressService.getById(id), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(this.errorMapper.createErrorMap(e), e.getStatusCode());
        }
    }
}
