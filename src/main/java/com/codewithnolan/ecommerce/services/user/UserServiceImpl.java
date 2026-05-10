package com.codewithnolan.ecommerce.services.user;

import com.codewithnolan.ecommerce.dtos.CreateUserDto;
import com.codewithnolan.ecommerce.dtos.UserMapper;
import com.codewithnolan.ecommerce.entities.user.User;
import com.codewithnolan.ecommerce.exceptions.UserException;
import com.codewithnolan.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkExistEmail(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    @Override
    public boolean checkExistPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).isEmpty();
    }

    @Override
    public String createUser(CreateUserDto createUserDto) {
        // TODO: Authentication
        boolean valid = this.checkExistEmail(createUserDto.getEmail())
                && this.checkExistPhoneNumber(createUserDto.getPhoneNumber());
        if (!valid) {
            throw new UserException("Email or phone number is existed!");
        }
        User user = UserMapper.mapDtoToEntity(createUserDto);
        System.out.println(user);
        userRepository.save(user);
        return "Created";
    }
}
