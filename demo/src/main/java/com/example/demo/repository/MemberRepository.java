package com.example.demo.repository;

import com.example.demo.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    @Query("SELECT m FROM Member m WHERE m.email = :email")
    Optional<Member> findMemberByEmail(String email);

    @Query("SELECT m FROM Member m WHERE m.username = :username")
    Optional<Member> findMemberByUsername(String username);
}
