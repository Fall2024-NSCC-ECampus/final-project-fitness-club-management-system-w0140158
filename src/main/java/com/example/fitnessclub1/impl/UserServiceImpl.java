package com.example.fitnessclub1.impl;

import com.example.fitnessclub1.entity.User;
import com.example.fitnessclub1.entity.Admin;
import com.example.fitnessclub1.repository.UserRepository;
import com.example.fitnessclub1.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.boot.CommandLineRunner;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        createDefaultAdmin();
    }

    private void createDefaultAdmin() {
        String defaultAdminUsername = "admin";
        String defaultAdminPassword = "password1";

        // Check if the admin user already exists
        if (!userRepository.findByUsername(defaultAdminUsername).isPresent()) {
            Admin adminUser = new Admin();
            adminUser.setUsername(defaultAdminUsername);
            adminUser.setPassword(passwordEncoder.encode(defaultAdminPassword));
            adminUser.setUserRole("ROLE_ADMIN");
            adminUser.setName("Patrick");
            adminUser.setEmail("adminpat@gmail.com");
            adminUser.setPhone("902-555-5555");

            userRepository.save(adminUser);
            System.out.println("Admin user created with default credentials (username: admin, password: password1).");
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRole("MEMBER");
        userRepository.save(user);
    }

    @Override
    public void registerUser(User user, String userType) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRole(userType == null ? "MEMBER" : userType.toUpperCase());
        userRepository.save(user);
    }

    @Override
    public void assignRoles(User user, Set<String> roles) {
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.findById(user.getId()).ifPresentOrElse(existingUser -> {
            existingUser.setUsername(user.getUsername());
            existingUser.setRoles(user.getRoles());
            userRepository.save(existingUser);
        }, () -> {
            throw new RuntimeException("User not found");
        });
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean validateUserCredentials(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getUserRole().split(",")) // Assuming roles are stored as a comma-separated string
                .accountLocked(false)
                .build();
    }
}
