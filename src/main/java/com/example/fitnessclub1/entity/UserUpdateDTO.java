package com.example.fitnessclub1.entity;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class UserUpdateDTO {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email address")
    private String email;

    private String phone;
    private Double weight;
    private Double height;
    private String shift;
    private String specialization;
}
