package com.codewithnolan.ecommerce.services.user;

import com.codewithnolan.ecommerce.dtos.*;
import com.codewithnolan.ecommerce.entities.user.Address;
import com.codewithnolan.ecommerce.entities.user.User;
import com.codewithnolan.ecommerce.exceptions.UserException;
import com.codewithnolan.ecommerce.repositories.UserRepository;
import com.codewithnolan.ecommerce.services.address.AddressService;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @NonNull private UserRepository userRepository;
    @NonNull private AddressService addressService;

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
        User user = UserMapper.mapCreateUserDtoToEntity(createUserDto);
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
    @Transactional
    public String updateProfile(UUID id, UpdateUserDto updateUserDto) {
        User user = this.getById(id);
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setAvatarUrl(updateUserDto.getAvatarUrl());
        user.setDob(updateUserDto.getDob());
        user.setGender(updateUserDto.getGender());
        userRepository.save(user);
        return "Updated";
    }

    @Override
    @Transactional
    public String updateAddresses(UUID id, List<AddressDto> addressesDto) {
        User user = this.getById(id);
        List<Address> addresses = addressesDto
                .stream()
                .map(AddressMapper::mapDtoToEntity)
                .collect(Collectors.toList());
        addresses.forEach(address -> {
            address.setUser(user);
            addressService.create(address);
        });
        user.setAddresses(addresses);
        userRepository.save(user);
        return "Updated";
    }
}
