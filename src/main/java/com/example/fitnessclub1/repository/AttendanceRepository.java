// src/main/java/com/example/fitnessclub1/repository/AttendanceRepository.java
package com.example.fitnessclub1.repository;

import com.example.fitnessclub1.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByMemberId(Long memberId);
    List<Attendance> findByTrainerId(Long trainerId);
}
