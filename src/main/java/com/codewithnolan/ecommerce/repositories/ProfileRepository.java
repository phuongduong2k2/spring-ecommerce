package com.codewithnolan.ecommerce.repositories;

import com.codewithnolan.ecommerce.entities.user.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
}
