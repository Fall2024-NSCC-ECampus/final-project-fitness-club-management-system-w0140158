package com.example.fitnessclub1.service;

import com.example.fitnessclub1.entity.Member;
import com.example.fitnessclub1.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    public Member addMember(Member member) {
        System.out.println("Attempting to save member: " + member);
        Member savedMember = memberRepository.save(member);
        System.out.println("Saved member: " + savedMember);
        return savedMember;
    }

    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
