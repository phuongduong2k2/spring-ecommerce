package com.codewithnolan.ecommerce.controllers.user;

import com.codewithnolan.ecommerce.dtos.AddressDto;
import com.codewithnolan.ecommerce.dtos.CreateUserDto;
import com.codewithnolan.ecommerce.dtos.UpdateAddressDto;
import com.codewithnolan.ecommerce.dtos.UpdateUserDto;
import com.codewithnolan.ecommerce.exceptions.ApiError;
import com.codewithnolan.ecommerce.exceptions.UserException;
import com.codewithnolan.ecommerce.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UUID> register(@Valid @RequestBody CreateUserDto createUserDto) {
        return new ResponseEntity<>(this.userService.createUser(createUserDto), HttpStatus.OK);
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<?> getProfile(@PathVariable UUID id) {
        return new ResponseEntity<>(this.userService.getById(id), HttpStatus.OK);
    }


    @PutMapping("/{id}/profile")
    public ResponseEntity<String> updateProfile(@PathVariable UUID id, @Valid @RequestBody UpdateUserDto updateUserDto) {
        return new ResponseEntity<>(this.userService.updateProfile(id, updateUserDto), HttpStatus.OK);
    }

    @PutMapping("/{id}/addresses")
    public ResponseEntity<?> updateAddresses(@PathVariable UUID id, @Valid @RequestBody UpdateAddressDto updateAddressDto) {
        return new ResponseEntity<>(this.userService.updateAddresses(id, updateAddressDto.getAddresses()), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleException(UserException ex) {
        ApiError apiError = new ApiError
                .Builder()
                .withMessage(ex.getMessage())
                .withHttpStatus(HttpStatus.BAD_REQUEST)
                .withCreatedAt()
                .build();
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }
}
