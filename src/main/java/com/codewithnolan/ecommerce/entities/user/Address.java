package com.codewithnolan.ecommerce.entities.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity(name = "addresses")
public class Address {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private UUID userId;
    private AddressType type;
    private String street;
    private String ward;
    private String district;
    private String city;
    private boolean isDefault;
}
