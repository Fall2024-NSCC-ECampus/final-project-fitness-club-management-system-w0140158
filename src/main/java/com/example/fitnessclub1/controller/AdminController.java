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

        adminService.addMember(member);
        redirectAttributes.addFlashAttribute("success", "Member added successfully!");
        return "redirect:/admin/dashboard";
    }


    @GetMapping("/members/edit/{id}")
    public String editMemberForm(@PathVariable Long id, Model model) {
        Member member = adminService.getMemberById(id);
        if (member == null) {
            throw new NoSuchElementException("Member not found with ID: " + id);
        }
        model.addAttribute("member", member);
        return "admin_edit_members";
    }

    @PostMapping("/members/edit/{id}")
    public String editMember(@PathVariable Long id,
                             @Valid @ModelAttribute("member") Member member,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin_edit_members";
        }
        adminService.updateMember(id, member);
        redirectAttributes.addFlashAttribute("success", "Member updated successfully!");
        return "redirect:/admin/members";
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
        return "redirect:/admin/members";
    }

    // Trainer Management
    @GetMapping("/trainers")
    public String listTrainers(Model model) {
        List<Trainer> trainers = adminService.getAllTrainers();
        model.addAttribute("trainers", trainers);
        return "admin_trainers";
    }

    @GetMapping("/trainers/add")
    public String addTrainerForm(Model model) {
        model.addAttribute("trainer", new Trainer());
        return "admin_add_trainers";
    }

    @PostMapping("/trainers/add")
    public String addTrainer(@Valid @ModelAttribute("trainer") Trainer trainer,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.out.println("Validation errors: " + result.getAllErrors());
            return "admin_add_trainers";
        }

        System.out.println("Adding Trainer: " + trainer);


        if (adminService.trainerExists(trainer.getUsername())) {
            redirectAttributes.addFlashAttribute("error", "A trainer with this username already exists.");
            return "redirect:/admin/trainers/add";
        }

        adminService.addTrainer(trainer);
        redirectAttributes.addFlashAttribute("success", "Trainer added successfully!");


        return "redirect:/admin/dashboard";
    }


    @GetMapping("/trainers/edit/{id}")
    public String editTrainerForm(@PathVariable Long id, Model model) {
        Trainer trainer = adminService.getTrainerById(id);
        if (trainer == null) {
            throw new NoSuchElementException("Trainer not found with ID: " + id);
        }
        model.addAttribute("trainer", trainer);
        return "admin_edit_trainer";
    }

    @PostMapping("/trainers/edit/{id}")
    public String editTrainer(@PathVariable Long id,
                              @Valid @ModelAttribute("trainer") Trainer trainer,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin_edit_trainer";
        }
        adminService.updateTrainer(id, trainer);
        redirectAttributes.addFlashAttribute("success", "Trainer updated successfully!");
        return "redirect:/admin/trainers";
    }

    @GetMapping("/trainers/delete/{id}")
    public String deleteTrainer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            adminService.deleteTrainer(id);
            redirectAttributes.addFlashAttribute("success", "Trainer deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete trainer with ID: " + id);
        }
        return "redirect:/admin/trainers";
    }
}