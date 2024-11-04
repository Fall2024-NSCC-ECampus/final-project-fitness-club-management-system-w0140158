package com.example.fitnessclub1.repository;

import com.example.fitnessclub1.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


    List<Schedule> findByTrainerId(Long trainerId);


    List<Schedule> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);


    @Query("SELECT s FROM Schedule s WHERE s.trainer.id = :trainerId AND s.startTime BETWEEN :start AND :end")
    List<Schedule> findSchedulesByTrainerAndDateRange(
            @Param("trainerId") Long trainerId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);


    List<Schedule> findByActivity(String activity);
}
