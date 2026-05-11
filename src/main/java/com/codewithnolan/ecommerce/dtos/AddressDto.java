package com.codewithnolan.ecommerce.dtos;

import com.codewithnolan.ecommerce.entities.user.AddressType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    @NotNull(message = "Address type if required")
    private AddressType type;

    @NotNull(message = "Street if required")
    private String street;

    @NotNull(message = "Ward if required")
    private String ward;

    @NotNull(message = "District if required")
    private String district;

    @NotNull(message = "City if required")
    private String city;

    private boolean isDefault;
}
