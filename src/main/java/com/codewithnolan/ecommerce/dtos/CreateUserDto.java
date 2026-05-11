package com.codewithnolan.ecommerce.dtos;

import com.codewithnolan.ecommerce.entities.user.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
