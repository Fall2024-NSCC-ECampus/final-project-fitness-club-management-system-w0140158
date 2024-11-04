package com.example.fitnessclub1.controller;

import com.example.fitnessclub1.entity.Schedule;
import com.example.fitnessclub1.entity.Member;
import com.example.fitnessclub1.service.MemberService;
import com.example.fitnessclub1.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final ScheduleService scheduleService;

    @Autowired
    public MemberController(MemberService memberService,
                            ScheduleService scheduleService) {
        this.memberService = memberService;
        this.scheduleService = scheduleService;
    }

    @GetMapping("/dashboard")
    public String memberDashboard() {
        return "member_dashboard";}

    @GetMapping("/schedule")
    public String viewSchedule(Model model) {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        model.addAttribute("schedules", schedules);
        return "member/schedule";
    }

    @GetMapping("/profile")
    public String viewProfile(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        model.addAttribute("member", member);
        return "member/profile";
    }
}