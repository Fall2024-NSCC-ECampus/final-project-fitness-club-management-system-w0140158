package com.example.fitnessclub1.service;

import com.example.fitnessclub1.entity.Member;
import com.example.fitnessclub1.entity.Trainer;
import com.example.fitnessclub1.repository.MemberRepository;
import com.example.fitnessclub1.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import java.util.List;

@Service
public class AdminService {

    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;

    @Autowired
    public AdminService(MemberRepository memberRepository, TrainerRepository trainerRepository) {
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
    }

    // Member Management
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public void addMember(Member member) {
        // Ensure all necessary fields are validated before saving
        memberRepository.save(member);
    }

    public boolean memberExists(String email) {
        // Checks if a member with the given email exists
        return memberRepository.findByEmail(email).isPresent();
    }

    public Member getMemberById(Long id) {
        // Fetch a member by ID, return null if not found
        return memberRepository.findById(id).orElse(null);
    }

    public void updateMember(Long id, @Valid Member member) {
        // Check if member exists and update their details
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Member not found");
        }
        member.setId(id); // Ensure the ID is set for the update
        memberRepository.save(member);
    }

    public void deleteMember(Long id) {
        // Attempt to delete a member by ID, handle any potential issues
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Member not found");
        }
        memberRepository.deleteById(id);
    }

    // Trainer Management
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }


    public void addTrainer(Trainer trainer) {
        trainerRepository.save(trainer);
    }

    public boolean trainerExists(String username) {
        return trainerRepository.findByUsername(username).isPresent();
    }

    public boolean trainerEmailExists(String email) {
        return trainerRepository.findByEmail(email).isPresent();
    }

    public Trainer getTrainerById(Long id) {
        return trainerRepository.findById(id).orElse(null);
    }

    public void updateTrainer(Long id, Trainer trainer) {
        trainer.setId(id); // Set the ID to the existing trainer
        trainerRepository.save(trainer);
    }

    public void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
    }
}
