package com.example.fitnessclub1.impl;

import com.example.fitnessclub1.entity.Attendance;
import com.example.fitnessclub1.repository.AttendanceRepository;
import com.example.fitnessclub1.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Ensure this annotation is present
public class AttendanceServiceImpl extends AttendanceService { // Correct implementation

    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public List<Attendance> getAttendanceByTrainerId(Long trainerId) {
        return List.of();
    }

    @Override
    public List<Attendance> getAttendanceByMemberId(Long memberId) {
        return List.of();
    }

    @Override
    public Attendance markAttendance(Attendance attendance) {
        attendanceRepository.save(attendance);
        return attendance;
    }

    @Override
    public List<Attendance> getAttendancesByMemberId(Long memberId) {
        return attendanceRepository.findByMemberId(memberId);
    }

    @Override
    public List<Attendance> getAttendancesByTrainerId(Long trainerId) {
        return attendanceRepository.findByTrainerId(trainerId);
    }
}
