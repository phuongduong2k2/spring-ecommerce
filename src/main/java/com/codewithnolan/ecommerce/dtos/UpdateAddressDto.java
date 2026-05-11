package com.codewithnolan.ecommerce.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdateAddressDto {
    @Valid
    @NotEmpty(message = "List address id required")
    private List<AddressDto> addresses = new ArrayList<>();
}
