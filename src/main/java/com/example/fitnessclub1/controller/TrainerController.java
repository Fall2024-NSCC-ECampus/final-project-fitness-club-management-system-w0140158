package com.example.fitnessclub1.controller;

import com.example.fitnessclub1.entity.Attendance;
import com.example.fitnessclub1.entity.Member;
import com.example.fitnessclub1.entity.Schedule;
import com.example.fitnessclub1.entity.Trainer;
import com.example.fitnessclub1.service.AttendanceService;
import com.example.fitnessclub1.service.ScheduleService;
import com.example.fitnessclub1.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Controller
@RequestMapping("/trainer")
public class TrainerController {

    private final TrainerService trainerService;
    private final ScheduleService scheduleService;
    private final AttendanceService attendanceService;

    @Autowired
    public TrainerController(TrainerService trainerService,
                             ScheduleService scheduleService,
                             AttendanceService attendanceService) {
        this.trainerService = trainerService;
        this.scheduleService = scheduleService;
        this.attendanceService = attendanceService;
    }

    @GetMapping("/login")
    public String login() {
        return "trainer/login";
    }

    @GetMapping("/dashboard")
    public String trainerDashboard() {
        return "trainer/dashboard";
    }

    @GetMapping("/schedule")
    public String viewSchedule(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Trainer trainer = (Trainer) trainerService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        List<Schedule> schedules = scheduleService.getSchedulesForTrainer(trainer.getId());
        model.addAttribute("schedules", schedules);
        return "trainer/schedule";
    }

    @GetMapping("/members")
    public String listMembers(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Trainer trainer = (Trainer) trainerService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        List<Member> members = trainerService.getAllMembersForTrainer(trainer.getId());
        model.addAttribute("members", members);
        return "trainer/members";
    }

    @PostMapping("/attendance")
    public String markAttendance(@ModelAttribute("attendance") Attendance attendance, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Trainer trainer = (Trainer) trainerService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        attendance.setTrainer(trainer);
        attendanceService.markAttendance(attendance);
        return "redirect:/trainer/members";
    }
}
