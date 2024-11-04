// src/main/java/com/example/fitnessclub1/service/impl/ScheduleServiceImpl.java
package com.example.fitnessclub1.impl;

import com.example.fitnessclub1.entity.Schedule;
import com.example.fitnessclub1.repository.ScheduleRepository;
import com.example.fitnessclub1.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleServiceImpl extends ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedule> getSchedulesForTrainer(Long trainerId) {
        return scheduleRepository.findByTrainerId(trainerId);
    }

    @Override
    public List<Schedule> getSchedulesWithinDateRange(LocalDateTime start, LocalDateTime end) {
        return scheduleRepository.findByStartTimeBetween(start, end);
    }

    @Override
    public List<Schedule> getSchedulesForTrainerWithinDateRange(Long trainerId, LocalDateTime start, LocalDateTime end) {
        return scheduleRepository.findSchedulesByTrainerAndDateRange(trainerId, start, end);
    }

    @Override
    public List<Schedule> getSchedulesByActivity(String activity) {
        return scheduleRepository.findByActivity(activity);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
}
