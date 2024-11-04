// src/main/java/com/example/fitnessclub1/impl/TrainerServiceImpl.java
package com.example.fitnessclub1.impl;

import com.example.fitnessclub1.entity.Member;
import com.example.fitnessclub1.entity.Trainer;
import com.example.fitnessclub1.repository.TrainerRepository;
import com.example.fitnessclub1.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    
    @Override
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer addTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public Trainer getTrainerById(Long id) {
        return trainerRepository.findById(id).orElse(null);
    }

    @Override
    public Trainer updateTrainer(Long id, @Valid Trainer trainer) {
        if (!trainerRepository.existsById(id)) {
            throw new RuntimeException("Trainer not found");
        }
        trainer.setId(id);
        return trainerRepository.save(trainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        if (!trainerRepository.existsById(id)) {
            throw new RuntimeException("Trainer not found");
        }
        trainerRepository.deleteById(id);
    }

    @Override
    public boolean trainerExists(String username) {
        return trainerRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean trainerEmailExists(String email) {
        return trainerRepository.findByEmail(email).isPresent();
    }

    @Override
    public Optional<Object> findByUsername(String username) {
        return Optional.empty(); // Implement logic if needed
    }

    @Override
    public List<Member> getAllMembersForTrainer(Long id) {
        return List.of(); // Implement logic if needed
    }
}
