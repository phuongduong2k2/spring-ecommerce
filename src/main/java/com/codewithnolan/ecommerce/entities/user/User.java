package com.codewithnolan.ecommerce.entities.user;

import com.codewithnolan.ecommerce.dtos.UpdateUserDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@ToString
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String email;
    private String password;
    private String phoneNumber;

    private String firstName;
    private String lastName;
    private String avatarUrl;
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.UNVERIFIED;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses = new ArrayList<>();

}
