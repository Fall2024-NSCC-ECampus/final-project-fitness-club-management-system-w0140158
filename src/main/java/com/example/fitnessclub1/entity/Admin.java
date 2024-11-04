package com.example.fitnessclub1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("ADMIN")
public class Admin extends User {



    // Getters and setters
    @Setter
    @Getter
    @NotBlank(message = "Name is required")
    private String name;


    @Getter
    private final String role = "ROLE_ADMIN";

    @Column(nullable = true)
    private String shift;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = true)
    private String specialization;

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

    public void setUserRole(String roleAdmin) {

    }
}
