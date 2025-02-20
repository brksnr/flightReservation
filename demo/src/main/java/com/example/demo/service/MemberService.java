package com.example.demo.service;


import com.example.demo.entity.member.Member;
import com.example.demo.exceptions.ApiException;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findMemberByUsername(username)
                .orElseThrow(() -> {
                    throw new ApiException("Kullanıcı bulunamadı!", HttpStatus.NOT_FOUND);
                });
    }

    public Member getMemberById(Long id){
        return memberRepository.findById(id)
                .orElseThrow(() ->{
                    throw new ApiException("Kullanıcı bulunamadı!", HttpStatus.NOT_FOUND);
                });
    }

    public Member getMemberByEmail(String email){
        return memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> {
                    throw new ApiException("Kullanıcı bulunamadı!", HttpStatus.NOT_FOUND);
                });
    }

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }
}
