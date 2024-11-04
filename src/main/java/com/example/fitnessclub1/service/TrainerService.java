// src/main/java/com/example/fitnessclub1/service/TrainerService.java
package com.example.fitnessclub1.service;

import com.example.fitnessclub1.entity.Member;
import com.example.fitnessclub1.entity.Trainer;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface TrainerService {
    List<Trainer> getAllTrainers();
    Trainer addTrainer(Trainer trainer);
    Trainer getTrainerById(Long id);
    Trainer updateTrainer(Long id, @Valid Trainer trainer);
    void deleteTrainer(Long id);
    boolean trainerExists(String username);
    boolean trainerEmailExists(String email);
    Optional<Object> findByUsername(String username);
    List<Member> getAllMembersForTrainer(Long id);
}
