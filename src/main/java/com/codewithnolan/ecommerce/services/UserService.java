package com.codewithnolan.ecommerce.services;

import com.codewithnolan.ecommerce.dto.UserDTO;
import com.codewithnolan.ecommerce.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UUID create(UserDTO userDto);

    List<UserDTO> getAll();

    User findById(UUID id);

    void remove(UUID id);

    String addShop(UUID userId, UUID shopId);
}
