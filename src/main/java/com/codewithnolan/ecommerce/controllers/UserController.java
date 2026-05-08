package com.codewithnolan.ecommerce.controllers;

import com.codewithnolan.ecommerce.dto.UserDTO;
import com.codewithnolan.ecommerce.entities.User;
import com.codewithnolan.ecommerce.services.UserService;
import com.codewithnolan.ecommerce.utils.ErrorMapper;
import com.codewithnolan.ecommerce.utils.UserMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Validated
@RestController
@RequestMapping(path = "/api/v1")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;
    private ErrorMapper errorMapper;

    @Autowired
    public UserController(UserService userService, UserMapper mapper, ErrorMapper errorMapper) {
        this.userService = userService;
        this.userMapper = mapper;
        this.errorMapper = errorMapper;
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody UserDTO userDto) {
        try {
            return new ResponseEntity<>(this.userService.create(userDto), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(this.errorMapper.createErrorMap(e), e.getStatusCode());
        }
    }

    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        try {
            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        } catch(ResponseStatusException e) {
            return new ResponseEntity<>(this.errorMapper.createErrorMap(e), e.getStatusCode());
        }
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> removeUser(@PathVariable UUID id) {
        userService.remove(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/users/{userId}/shops/{shopId}")
    public ResponseEntity<String> addShopToUser(@PathVariable UUID userId, @PathVariable UUID shopId) {
        String message = userService.addShop(userId, shopId);
        return ResponseEntity.ok(message);
    }

}
