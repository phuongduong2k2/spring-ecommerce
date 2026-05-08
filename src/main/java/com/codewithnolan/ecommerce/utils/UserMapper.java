package com.codewithnolan.ecommerce.utils;

import com.codewithnolan.ecommerce.dto.UserDTO;
import com.codewithnolan.ecommerce.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static User mapDtoToEntity(UserDTO userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setAddresses(userDto.getAddresses());
        return user;
    }

    public static UserDTO mapEntityToDto(User user) {
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddresses());
    }
}
