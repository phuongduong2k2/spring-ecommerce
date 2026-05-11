package com.codewithnolan.ecommerce.dtos;

import com.codewithnolan.ecommerce.entities.user.Profile;

public class ProfileMapper {
    public static Profile mapDtoToEntity(ProfileDto profileDto) {
        Profile profile = new Profile();
        profile.setFirstName(profileDto.getFirstName());
        profile.setLastName(profileDto.getLastName());
        profile.setAvatarUrl(profileDto.getAvatarUrl());
        profile.setDob(profileDto.getDob());
        profile.setGender(profileDto.getGender());
        return profile;
    }

    public static ProfileDto mapEntityToDto(Profile profile) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setFirstName(profile.getFirstName());
        profileDto.setLastName(profile.getLastName());
        profileDto.setAvatarUrl(profile.getAvatarUrl());
        profileDto.setDob(profile.getDob());
        profileDto.setGender(profile.getGender());
        return profileDto;
    }
}
