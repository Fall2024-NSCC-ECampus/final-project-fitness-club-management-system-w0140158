// src/main/java/com/example/fitnessclub1/repository/ScheduleRepository.java
package com.example.fitnessclub1.repository;

import com.example.fitnessclub1.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Find all schedules for a specific trainer
    List<Schedule> findByTrainerId(Long trainerId);

    // Find schedules that fall within a specific date and time range
    List<Schedule> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    // Find all schedules for a specific trainer within a date range
    @Query("SELECT s FROM Schedule s WHERE s.trainer.id = :trainerId AND s.startTime BETWEEN :start AND :end")
    List<Schedule> findSchedulesByTrainerAndDateRange(
            @Param("trainerId") Long trainerId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    // Retrieve schedules for a specific activity type (e.g., Yoga, Cardio)
    List<Schedule> findByActivity(String activity);
}
