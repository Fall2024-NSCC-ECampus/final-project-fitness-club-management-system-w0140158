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
        memberRepository.save(member);
    }

    public boolean memberExists(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public void updateMember(Long id, @Valid Member member) {
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Member not found");
        }
        member.setId(id);
        memberRepository.save(member);
    }

    public void deleteMember(Long id) {
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
        trainer.setId(id);
        trainerRepository.save(trainer);
    }

    public void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
    }
}
