package com.codewithnolan.ecommerce.services.address;

import com.codewithnolan.ecommerce.entities.user.Address;
import com.codewithnolan.ecommerce.repositories.AddressRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    @NonNull private AddressRepository addressRepository;

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }
}
