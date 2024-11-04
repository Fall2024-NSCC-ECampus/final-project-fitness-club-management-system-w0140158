// src/main/java/com/example/fitnessclub1/service/AttendanceService.java
package com.example.fitnessclub1.repository;

import com.example.fitnessclub1.entity.Attendance;

import java.util.List;

public interface AttendanceService {
    List<Attendance> getAttendanceByTrainerId(Long trainerId);

    List<Attendance> getAttendanceByMemberId(Long memberId);

    Attendance markAttendance(Attendance attendance);

    List<Attendance> getAttendancesByMemberId(Long memberId);

    List<Attendance> getAttendancesByTrainerId(Long trainerId);
}
