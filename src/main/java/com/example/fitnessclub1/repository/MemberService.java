package com.example.fitnessclub1.repository;

import com.example.fitnessclub1.entity.Member;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findByUsername(String username);
    Member getMemberById(Long memberId);
    Member updateMember(Member member);
    Member createMember(Member member);
    boolean existsByEmail(String email);
    void deleteMember(Long memberId);
}
