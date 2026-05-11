package com.codewithnolan.ecommerce.repositories;

import com.codewithnolan.ecommerce.entities.user.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM users u WHERE u.email=:email")
    Optional<User> findByEmail(@NotNull String email);

    @Query("SELECT u FROM users u WHERE u.phoneNumber=:phoneNumber")
    Optional<User> findByPhoneNumber(@NotNull String phoneNumber);

    @Query("SELECT u FROM users u WHERE u.id=:id")
    Optional<User> findById(@NotNull UUID id);
}
