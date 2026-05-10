package com.codewithnolan.ecommerce.services.user;

import com.codewithnolan.ecommerce.dtos.CreateUserDto;
import com.codewithnolan.ecommerce.entities.user.User;

public interface UserService {
    boolean checkExistEmail(String email);

    boolean checkExistPhoneNumber(String phoneNumber);

    String createUser(CreateUserDto createUserDto);
}
