package com.codewithnolan.ecommerce.repositories;

import com.codewithnolan.ecommerce.entities.User;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @NonNull
    @Query("SELECT u FROM users u")
    List<User> findAll();

    @NonNull
    @Query("SELECT u FROM users u WHERE u.id=:id")
    Optional<User> findById(@Param("id") @NonNull UUID id);

    @Modifying
    @Transactional
    @Query("UPDATE users u SET u.isDeleted=true WHERE u.id=:id")
    void deleteById(@Param("id") @NonNull UUID id);
}
