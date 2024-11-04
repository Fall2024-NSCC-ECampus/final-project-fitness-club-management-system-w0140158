package com.example.fitnessclub1.service;

import com.example.fitnessclub1.entity.Attendance;
import com.example.fitnessclub1.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public abstract List<Attendance> getAttendanceByTrainerId(Long trainerId);

    public abstract List<Attendance> getAttendanceByMemberId(Long memberId);

    public void markAttendance(Attendance attendance) {
        attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendancesByMemberId(Long memberId) {
        return attendanceRepository.findByMemberId(memberId);
    }


    public List<Attendance> getAttendancesByTrainerId(Long trainerId) {
        return attendanceRepository.findByTrainerId(trainerId);
    }
}
