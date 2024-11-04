// src/main/java/com/example/fitnessclub1/dto/UserRegistrationDTO.java
package com.example.fitnessclub1.entity;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class UserRegistrationDTO {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "User type is required")
    private String userType; // ADMIN, TRAINER, MEMBER

    // Common fields
    @NotBlank(message = "Name is required")
    private String name;

    private String shift;
    private String specialization;

    // Member-specific fields
    @Email(message = "Invalid email address")
    private String email;

    private String phone;
    private Double weight;
    private Double height;
}
