package com.example.fitnessclub1.controller;

import com.example.fitnessclub1.entity.Member;
import com.example.fitnessclub1.entity.Trainer;
import com.example.fitnessclub1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Dashboard
    @GetMapping("/dashboard")
    public String showAdminDashboard(Model model) {
        List<Member> members = adminService.getAllMembers();
        List<Trainer> trainers = adminService.getAllTrainers();
        model.addAttribute("members", members);
        model.addAttribute("trainers", trainers);

        return "admin_dashboard";
    }

    // Member Management
    @GetMapping("/members")
    public String listMembers(Model model) {
        List<Member> members = adminService.getAllMembers();
        model.addAttribute("members", members);
        return "admin_members"; // Returns the members template
    }

    @GetMapping("/members/add")
    public String addMemberForm(Model model) {
        model.addAttribute("member", new Member());
        return "admin_add_members"; // Show form to add a new member
    }

    @PostMapping("/members/add")
    public String addMember(@Valid @ModelAttribute("member") Member member,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.out.println("Validation errors: " + result.getAllErrors());
            return "admin_add_members"; // Return to form if validation fails
        }

        // Log the member details before adding
        System.out.println("Adding member: " + member);

        // Check for duplicate email
        if (adminService.memberExists(member.getEmail())) {
            redirectAttributes.addFlashAttribute("error", "A member with this email already exists.");
            return "redirect:/admin/members/add"; // Redirect back to form
        }

        adminService.addMember(member); // Ensure this is correctly implemented
        redirectAttributes.addFlashAttribute("success", "Member added successfully!");
        return "redirect:/admin/dashboard"; // Redirect to the dashboard
    }


    @GetMapping("/members/edit/{id}")
    public String editMemberForm(@PathVariable Long id, Model model) {
        Member member = adminService.getMemberById(id);
        if (member == null) {
            throw new NoSuchElementException("Member not found with ID: " + id);
        }
        model.addAttribute("member", member);
        return "admin_edit_members"; // Show form to edit the member
    }

    @PostMapping("/members/edit/{id}")
    public String editMember(@PathVariable Long id,
                             @Valid @ModelAttribute("member") Member member,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin_edit_members"; // Return to form if validation fails
        }
        adminService.updateMember(id, member); // Update member in service
        redirectAttributes.addFlashAttribute("success", "Member updated successfully!");
        return "redirect:/admin/members"; // Redirect after successful update
    }

    @GetMapping("/members/delete/{id}")
    public String deleteMember(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            adminService.deleteMember(id);
            redirectAttributes.addFlashAttribute("success", "Member deleted successfully!");
        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("error", "Member not found.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete member with ID: " + id);
        }
        return "redirect:/admin/members"; // Redirect to members listing
    }





    // Trainer Management
    @GetMapping("/trainers")
    public String listTrainers(Model model) {
        List<Trainer> trainers = adminService.getAllTrainers();
        model.addAttribute("trainers", trainers);
        return "admin_trainers"; // Returns the trainers template
    }

    @GetMapping("/trainers/add")
    public String addTrainerForm(Model model) {
        model.addAttribute("trainer", new Trainer());
        return "admin_add_trainers"; // Show form to add a new trainer
    }

    @PostMapping("/trainers/add")
    public String addTrainer(@Valid @ModelAttribute("trainer") Trainer trainer,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.out.println("Validation errors: " + result.getAllErrors());
            return "admin_add_trainers"; // Return to form if validation fails
        }

        System.out.println("Adding Trainer: " + trainer);

        // Check for duplicate username
        if (adminService.trainerExists(trainer.getUsername())) {
            redirectAttributes.addFlashAttribute("error", "A trainer with this username already exists.");
            return "redirect:/admin/trainers/add"; // Redirect back to form
        }

        adminService.addTrainer(trainer); // Save the new trainer
        redirectAttributes.addFlashAttribute("success", "Trainer added successfully!");

        // Redirect to the dashboard to see the updated list of trainers
        return "redirect:/admin/dashboard"; // Redirect to the dashboard
    }


    @GetMapping("/trainers/edit/{id}")
    public String editTrainerForm(@PathVariable Long id, Model model) {
        Trainer trainer = adminService.getTrainerById(id);
        if (trainer == null) {
            throw new NoSuchElementException("Trainer not found with ID: " + id);
        }
        model.addAttribute("trainer", trainer);
        return "admin_edit_trainer"; // Show form to edit the trainer
    }

    @PostMapping("/trainers/edit/{id}")
    public String editTrainer(@PathVariable Long id,
                              @Valid @ModelAttribute("trainer") Trainer trainer,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin_edit_trainer"; // Return to form if validation fails
        }
        adminService.updateTrainer(id, trainer); // Update trainer in service
        redirectAttributes.addFlashAttribute("success", "Trainer updated successfully!");
        return "redirect:/admin/trainers"; // Redirect after successful update
    }

    @GetMapping("/trainers/delete/{id}")
    public String deleteTrainer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            adminService.deleteTrainer(id);
            redirectAttributes.addFlashAttribute("success", "Trainer deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete trainer with ID: " + id);
        }
        return "redirect:/admin/trainers"; // Redirect to trainers listing
    }
}