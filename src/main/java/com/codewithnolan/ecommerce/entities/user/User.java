package com.codewithnolan.ecommerce.entities.user;

import jakarta.persistence.Entity;

import java.util.UUID;

@Entity(name = "users")
public class User {
    private UUID id;
    private String email;
    private String password;
    private String phone;
    private UserRole role;
    private UserStatus status;


}
