package com.example.fitnessclub1.repository;

import com.example.fitnessclub1.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByUsername(String username);
    Optional<Trainer> findByEmail(String email);
}
