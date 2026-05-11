package com.codewithnolan.ecommerce.services.profile;

import com.codewithnolan.ecommerce.entities.user.Profile;
import com.codewithnolan.ecommerce.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public void createProfile(Profile profile) {
        profileRepository.save(profile);
    }
}
