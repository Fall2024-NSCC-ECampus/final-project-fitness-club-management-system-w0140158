package com.example.fitnessclub1.service;

import com.example.fitnessclub1.entity.Schedule;
import com.example.fitnessclub1.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> getSchedulesForTrainer(Long trainerId) {
        return scheduleRepository.findByTrainerId(trainerId);
    }

    public List<Schedule> getSchedulesWithinDateRange(LocalDateTime start, LocalDateTime end) {
        return scheduleRepository.findByStartTimeBetween(start, end);
    }

    public List<Schedule> getSchedulesForTrainerWithinDateRange(Long trainerId, LocalDateTime start, LocalDateTime end) {
        return scheduleRepository.findSchedulesByTrainerAndDateRange(trainerId, start, end);
    }

    public List<Schedule> getSchedulesByActivity(String activity) {
        return scheduleRepository.findByActivity(activity);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
}
