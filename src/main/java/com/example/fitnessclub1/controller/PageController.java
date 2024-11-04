// src/main/java/com/example/fitnessclub1/controller/PageController.java
package com.example.fitnessclub1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Maps to login.html in /templates
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // Maps to register.html in /templates
    }

    @GetMapping("/dashboard")
    public String showDashboardPage() {
        return "dashboard"; // Maps to dashboard.html in /templates
    }
}
