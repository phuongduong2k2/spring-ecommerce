package com.codewithnolan.ecommerce.dtos;

import com.codewithnolan.ecommerce.entities.user.User;

public class UserMapper {
    public static User mapDtoToEntity(CreateUserDto createUserDto) {
        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setPhoneNumber(createUserDto.getPhoneNumber());
        return user;
    }

}
