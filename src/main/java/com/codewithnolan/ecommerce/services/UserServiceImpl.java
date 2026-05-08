package com.codewithnolan.ecommerce.services;

import com.codewithnolan.ecommerce.dto.UserDTO;
import com.codewithnolan.ecommerce.entities.Address;
import com.codewithnolan.ecommerce.entities.Shop;
import com.codewithnolan.ecommerce.entities.User;
import com.codewithnolan.ecommerce.exception.EntityNotFoundException;
import com.codewithnolan.ecommerce.repositories.UserRepository;
import com.codewithnolan.ecommerce.utils.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AddressService addressService;
    private ShopService shopService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AddressService addressService, ShopService shopService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
        this.shopService = shopService;
    }

    @Override
    @Transactional
    public UUID create(UserDTO userDto) {
        if (userDto.getAddresses() != null) {
            for (Address address : userDto.getAddresses()) {
                addressService.create(address);
            }
        }
        User user = UserMapper.mapDtoToEntity(userDto);
        User createdUser = userRepository.save(user);
        return createdUser.getUUID();
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository
                .findAll()
                .stream()
                .map(UserMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Employee Not Found with id: " + id)
        );
    }

    @Override
    public void remove(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public String addShop(UUID userId, UUID shopId) {
        User user = findById(userId);
        Shop shop = shopService.findById(shopId);
        boolean status = user.addShop(shop);
        userRepository.save(user);
        return status ? "Shop added to User successfully" : "User already follows this Shop";
    }
}
