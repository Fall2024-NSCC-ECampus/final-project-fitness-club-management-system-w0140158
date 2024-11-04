// src/main/java/com/example/fitnessclub1/service/AdminServiceImpl.java
package com.example.fitnessclub1.impl;

import com.example.fitnessclub1.entity.Member;
import com.example.fitnessclub1.entity.Trainer;
import com.example.fitnessclub1.repository.MemberRepository;
import com.example.fitnessclub1.repository.TrainerRepository;
import com.example.fitnessclub1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends AdminService { // Changed from extends to implements

    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;

    @Autowired
    public AdminServiceImpl(MemberRepository memberRepository, TrainerRepository trainerRepository) {
        super(memberRepository, trainerRepository);
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
    }

    // Member Operations
    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public void addMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public void updateMember(Long id, Member member) {
        memberRepository.save(member);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    // Trainer Operations
    @Override
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public void addTrainer(Trainer trainer) {
        trainerRepository.save(trainer);
    }

    @Override
    public Trainer getTrainerById(Long id) {
        return trainerRepository.findById(id).orElse(null);
    }

    @Override
    public void updateTrainer(Long id, Trainer trainer) {
        trainerRepository.save(trainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
    }
}
