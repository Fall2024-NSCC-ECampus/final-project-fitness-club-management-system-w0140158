package com.example.fitnessclub1.controller;

import com.example.fitnessclub1.entity.Member;
import com.example.fitnessclub1.entity.Trainer;
import com.example.fitnessclub1.entity.User;
import com.example.fitnessclub1.entity.UserRegistrationDTO;
import com.example.fitnessclub1.repository.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    @Autowired
    public AuthController(@Lazy UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login"; // Ensure login.html exists in templates folder
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        logger.info("Login attempt for user: {}", username);
        boolean loginSuccessful = userService.validateUserCredentials(username, password);
        if (loginSuccessful) {
            User user = userService.findByUsername(username).orElse(null);
            if (user != null) {
                String role = user.getUserRole();
                logger.info("User role: {}", role);
                switch (role) {
                    case "ROLE_ADMIN":
                        return "admin_dashboard"; // Admin dashboard view
                    case "ROLE_TRAINER":
                        return "trainer_dashboard"; // Trainer dashboard view
                    case "ROLE_MEMBER":
                    default:
                        return "member_dashboard"; // Member dashboard view
                }
            }
        }
        model.addAttribute("error", "Invalid username or password.");
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
        return "register"; // Ensure register.html exists in templates folder
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserRegistrationDTO registrationDTO,
                               BindingResult result,
                               Model model) {
        logger.info("Processing registration for username: {}", registrationDTO.getUsername());

        if (result.hasErrors()) {
            logger.warn("Validation errors found: {}", result.getAllErrors());
            return "register";
        }

        try {
            if ("ADMIN".equalsIgnoreCase(registrationDTO.getUserType())) {
                model.addAttribute("error", "Invalid role selection.");
                logger.warn("Attempt to register with ADMIN role");
                return "register";
            }

            if (userService.findByUsername(registrationDTO.getUsername()).isPresent()) {
                model.addAttribute("error", "Username is already taken.");
                logger.warn("Username already taken: {}", registrationDTO.getUsername());
                return "register";
            }

            if ("MEMBER".equalsIgnoreCase(registrationDTO.getUserType()) &&
                    userService.existsByEmail(registrationDTO.getEmail())) {
                model.addAttribute("error", "Email is already in use.");
                logger.warn("Email already in use: {}", registrationDTO.getEmail());
                return "register";
            }

            User user = createUserBasedOnType(registrationDTO);
            userService.registerUser(user);

            model.addAttribute("message", "Registration successful! Please log in.");
            logger.info("Registration successful for username: {}", registrationDTO.getUsername());
            return "redirect:/auth/login";
        } catch (Exception e) {
            logger.error("Registration failed for username: {}", registrationDTO.getUsername(), e);
            model.addAttribute("error", "Registration failed. Please try again.");
            return "register";
        }
    }

    private User createUserBasedOnType(UserRegistrationDTO registrationDTO) {
        String userType = registrationDTO.getUserType().toUpperCase();
        switch (userType) {
            case "TRAINER":
                Trainer trainer = new Trainer();
                populateCommonUserFields(trainer, registrationDTO);
                trainer.setSpecialization(registrationDTO.getSpecialization());
                trainer.setShift(registrationDTO.getShift());
                return trainer;
            case "MEMBER":
            default:
                Member member = new Member();
                populateCommonUserFields(member, registrationDTO);
                member.setEmail(registrationDTO.getEmail());
                member.setPhone(registrationDTO.getPhone());
                member.setWeight(registrationDTO.getWeight());
                member.setHeight(registrationDTO.getHeight());
                return member;
        }
    }

    private void populateCommonUserFields(User user, UserRegistrationDTO registrationDTO) {
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(registrationDTO.getPassword());
        user.setName(registrationDTO.getName());
    }
}
