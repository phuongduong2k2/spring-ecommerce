package com.codewithnolan.ecommerce.dto;

import com.codewithnolan.ecommerce.entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class UserDTO {
        @NotBlank(message = "First name cannot be blank")
        private String firstName;

        @NotBlank(message = "Last name cannot be blank")
        private String lastName;

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email should be valid", regexp = "^[A-Za-z0-9+_.-]+@gmail.com")
        private String email;

        private List<Address> addresses;

        public UserDTO(String firstName, String lastName, String email, List<Address> addresses) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.addresses = addresses;
        }

        public String getFirstName() {
                return firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public String getEmail() {
                return email;
        }

        public List<Address> getAddresses() {
                return addresses;
        }
}
