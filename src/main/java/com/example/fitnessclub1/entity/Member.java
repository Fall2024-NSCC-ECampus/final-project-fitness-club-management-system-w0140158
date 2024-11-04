package com.example.fitnessclub1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("MEMBER")
public class Member extends User {

    @Getter
    private final String role = "ROLE_MEMBER"; // You might not need this if inherited

    @NotBlank(message = "Name is mandatory")
    @Column(nullable = false)
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Phone is mandatory")
    @Column(nullable = false)
    private String phone;

    private Double weight;
    private Double height;

    @Column(nullable = true)
    private String shift;

    @Column(nullable = true)
    private String specialization;
}
