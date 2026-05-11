package com.codewithnolan.ecommerce.controllers.user;

import com.codewithnolan.ecommerce.dtos.ProfileDto;
import com.codewithnolan.ecommerce.dtos.CreateUserDto;
import com.codewithnolan.ecommerce.exceptions.ApiError;
import com.codewithnolan.ecommerce.exceptions.UserException;
import com.codewithnolan.ecommerce.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}/profile")
    public ResponseEntity<String> updateProfile(@PathVariable UUID id, @Valid @RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(this.userService.updateProfile(id, profileDto), HttpStatus.OK);
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
