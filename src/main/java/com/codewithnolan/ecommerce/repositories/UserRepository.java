package com.codewithnolan.ecommerce.repositories;

import com.codewithnolan.ecommerce.entities.user.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM users u WHERE u.email=:email")
    Optional<User> findByEmail(@NonNull String email);

    @Query("SELECT u FROM users u WHERE u.phoneNumber=:phoneNumber")
    Optional<User> findByPhoneNumber(@NonNull String phoneNumber);

    @NonNull
    @Query("SELECT u FROM users u WHERE u.id=:id")
    Optional<User> findById(@NonNull UUID id);
}
