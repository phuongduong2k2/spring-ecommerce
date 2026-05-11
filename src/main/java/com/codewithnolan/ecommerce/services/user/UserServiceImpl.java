package com.codewithnolan.ecommerce.services.user;

import com.codewithnolan.ecommerce.dtos.ProfileDto;
import com.codewithnolan.ecommerce.dtos.CreateUserDto;
import com.codewithnolan.ecommerce.dtos.ProfileMapper;
import com.codewithnolan.ecommerce.dtos.UserMapper;
import com.codewithnolan.ecommerce.entities.user.Profile;
import com.codewithnolan.ecommerce.entities.user.User;
import com.codewithnolan.ecommerce.exceptions.UserException;
import com.codewithnolan.ecommerce.repositories.ProfileRepository;
import com.codewithnolan.ecommerce.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @NonNull private UserRepository userRepository;
    @NonNull private ProfileRepository profileRepository;

    @Override
    public boolean checkExistEmail(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    @Override
    public boolean checkExistPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).isEmpty();
    }

    @Override
    public UUID createUser(CreateUserDto createUserDto) {
        // TODO: Authentication
        boolean valid = this.checkExistEmail(createUserDto.getEmail())
                && this.checkExistPhoneNumber(createUserDto.getPhoneNumber());
        if (!valid) {
            throw new UserException("Email or phone number is existed!");
        }
        User user = UserMapper.mapDtoToEntity(createUserDto);
        return userRepository.save(user).getId();
    }

    @Override
    public User getById(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserException("User not found");
        }
        return optionalUser.get();
    }

    @Override
    public String updateProfile(UUID id, ProfileDto profileDto) {
        User user = this.getById(id);
        Profile profile = ProfileMapper.mapDtoToEntity(profileDto);
        user.setProfile(profile);
        userRepository.save(user);
        return "Updated";
    }
}
