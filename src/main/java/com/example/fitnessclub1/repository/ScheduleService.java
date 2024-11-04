package com.example.fitnessclub1.repository;

import com.example.fitnessclub1.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    List<Schedule> getSchedulesForTrainer(Long trainerId);
    List<Schedule> getSchedulesWithinDateRange(LocalDateTime start, LocalDateTime end);
    List<Schedule> getSchedulesForTrainerWithinDateRange(Long trainerId, LocalDateTime start, LocalDateTime end);
    List<Schedule> getSchedulesByActivity(String activity);
    List<Schedule> getAllSchedules();
}
