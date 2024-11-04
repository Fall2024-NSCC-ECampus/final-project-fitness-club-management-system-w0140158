package com.example.fitnessclub1.repository;

import com.example.fitnessclub1.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Object> findByEmail(@Email(message = "Email should be valid") @NotBlank(message = "Email is mandatory") String email);

    Optional<Member> findByUsername(String username);

    List<Member> findByUserType(String member);
}