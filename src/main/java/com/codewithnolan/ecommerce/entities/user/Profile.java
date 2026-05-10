package com.codewithnolan.ecommerce.entities.user;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private LocalDate dob;
    private Gender gender;

    @OneToOne(mappedBy = "profile")
    private User user;
}
