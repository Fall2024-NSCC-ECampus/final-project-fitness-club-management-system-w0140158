package com.example.fitnessclub1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.ConnectionBuilder;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
public abstract class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Username is required")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;

    @Column(name = "user_type", insertable = false, updatable = false)
    private String userType;

    private String userRole;

    public static User withUsername(@NotBlank(message = "Username is required") String username) {
        User user = new User() {
            @Override
            public String getName() { return null; }
            @Override
            public void setName(String name) {}
            @Override
            public String getEmail() { return null; }
            @Override
            public void setEmail(String email) {}
            @Override
            public String getPhone() { return null; }
            @Override
            public void setPhone(String phone) {}
            @Override
            public Double getWeight() { return null; }
            @Override
            public void setWeight(Double weight) {}
            @Override
            public Double getHeight() { return null; }
            @Override
            public void setHeight(Double height) {}
            @Override
            public String getShift() { return null; }
            @Override
            public void setShift(String shift) {}
            @Override
            public String getSpecialization() { return null; }
            @Override
            public void setSpecialization(String specialization) {}
        };
        user.setUsername(username);
        return user;
    }

    public void setPassword(@NotBlank(message = "Password is required") String password) {
        this.password = password;
    }


    // UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole.startsWith("ROLE_") ? userRole : "ROLE_" + userRole;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    public String getRole() { return this.userRole; }

    // Abstract methods to be implemented in subclasses
    public abstract String getName();
    public abstract void setName(String name);
    public abstract String getEmail();
    public abstract void setEmail(String email);
    public abstract String getPhone();
    public abstract void setPhone(String phone);
    public abstract Double getWeight();
    public abstract void setWeight(Double weight);
    public abstract Double getHeight();
    public abstract void setHeight(Double height);
    public abstract String getShift();
    public abstract void setShift(String shift);
    public abstract String getSpecialization();
    public abstract void setSpecialization(String specialization);

}
