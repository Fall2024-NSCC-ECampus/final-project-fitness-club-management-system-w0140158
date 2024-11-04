package com.example.fitnessclub1.repository;

import com.example.fitnessclub1.entity.User;
import jakarta.validation.constraints.Email;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import java.util.Set;

public interface UserService extends UserDetailsService {

    Optional<User> findByUsername(String username);
    void registerUser(User user);
    void registerUser(User user, String userType);
    void assignRoles(User user, Set<String> roles);
    void updateUser(User user);
    void changePassword(User user, String newPassword);
    void deleteUser(Long userId);
    boolean existsByEmail(@Email(message = "Invalid email address") String email);
    boolean validateUserCredentials(String username, String password);
}
