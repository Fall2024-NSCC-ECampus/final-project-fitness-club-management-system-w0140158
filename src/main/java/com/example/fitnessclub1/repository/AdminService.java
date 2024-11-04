package com.example.fitnessclub1.repository;

import com.example.fitnessclub1.entity.Member;
import com.example.fitnessclub1.entity.Trainer;

import java.util.List;

public interface AdminService {
    // Member Operations
    List<Member> getAllMembers();

    Member addMember(Member member);

    Member getMemberById(Long id);

    Member updateMember(Member member);

    void deleteMember(Long id);

    // Trainer Operations
    List<Trainer> getAllTrainers();

    Trainer addTrainer(Trainer trainer);

    Trainer getTrainerById(Long id);

    Trainer updateTrainer(Trainer trainer);

    void deleteTrainer(Long id);
}
