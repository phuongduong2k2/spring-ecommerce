package com.codewithnolan.ecommerce.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid!")
    private String email;

    @NotBlank(message = "Email is required")
    private String password;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
}
