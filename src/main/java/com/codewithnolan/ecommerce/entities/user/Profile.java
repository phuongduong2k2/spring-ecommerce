package com.codewithnolan.ecommerce.entities.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private LocalDate dob;
    private Gender gender;

    @OneToOne(mappedBy = "profile")
    @Setter(AccessLevel.NONE)
    private User user;
}
