package com.codewithnolan.ecommerce.dtos;

import com.codewithnolan.ecommerce.entities.user.Address;

public class AddressMapper {
    public static Address mapDtoToEntity(AddressDto addressDto) {
        Address address = new Address();
        address.setDistrict(addressDto.getDistrict());
        address.setWard(addressDto.getWard());
        address.setCity(addressDto.getCity());
        address.setType(addressDto.getType());
        address.setStreet(addressDto.getStreet());
        return address;
    }
}
