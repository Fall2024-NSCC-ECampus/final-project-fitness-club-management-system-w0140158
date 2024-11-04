// src/main/java/com/example/fitnessclub1/entity/Trainer.java
package com.example.fitnessclub1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("TRAINER")
public class Trainer extends User {

    @NotBlank(message = "Name is mandatory")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Specialization is mandatory")
    @Column(nullable = false)
    private String specialization;

    @NotBlank(message = "Shift is mandatory")
    @Column(nullable = false)
    private String shift;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Column(nullable = false, unique = true)
    private String email; // Include email field

    @NotBlank(message = "Phone is mandatory")
    @Column(nullable = false)
    private String phone; // Include phone field

    public Trainer(String username, String password, String name, String specialization, String shift, String email, String phone) {
        super.setUsername(username);
        super.setPassword(password);
        this.name = name;
        this.specialization = specialization;
        this.shift = shift;
        this.email = email; // Initialize email
        this.phone = phone; // Initialize phone

        // Add the role to the roles set in the User class
        Set<String> roles = new HashSet<>();
        roles.add("TRAINER");
        super.setRoles(roles); // Ensure you have setRoles in User class
    }

    @Override
    public Double getWeight() {
        return 0.0;
    }

    @Override
    public void setWeight(Double weight) {

    }

    @Override
    public Double getHeight() {
        return 0.0;
    }

    @Override
    public void setHeight(Double height) {

    }

}
