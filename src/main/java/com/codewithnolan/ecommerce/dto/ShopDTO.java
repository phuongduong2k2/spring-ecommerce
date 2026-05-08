package com.codewithnolan.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public record ShopDTO(
        @NotBlank(message = "Name cannot be empty")
        String name
) {}
