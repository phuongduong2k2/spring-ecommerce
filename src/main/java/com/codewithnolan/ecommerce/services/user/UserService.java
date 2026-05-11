package com.codewithnolan.ecommerce.services.user;

import com.codewithnolan.ecommerce.dtos.AddressDto;
import com.codewithnolan.ecommerce.dtos.CreateUserDto;
import com.codewithnolan.ecommerce.dtos.UpdateUserDto;
import com.codewithnolan.ecommerce.entities.user.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    boolean checkExistEmail(String email);

    boolean checkExistPhoneNumber(String phoneNumber);

    UUID createUser(CreateUserDto createUserDto);

    User getById(UUID id);

    String updateProfile(UUID id, UpdateUserDto updateUserDto);

    String updateAddresses(UUID id, List<AddressDto> addressesDto);
}
