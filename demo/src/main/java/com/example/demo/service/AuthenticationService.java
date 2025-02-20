package com.example.demo.service;

import com.example.demo.entity.member.Member;
import com.example.demo.exceptions.ApiException;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Member register(String username, String email, String password){
        if(username == null || username.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty())
        {
            throw  new ApiException("Tüm alanları doldurunuz!", HttpStatus.BAD_REQUEST);
        }
        if(memberRepository.findMemberByEmail(email).isPresent()){
            throw new ApiException("Bu email başka bir kullanıcı tarafından kullanılıyor!", HttpStatus.BAD_REQUEST);
        }
        if(memberRepository.findMemberByUsername(username).isPresent()){
            throw new ApiException("Bu username başka bir kullanıcı tarafından kullanılıyor!", HttpStatus.BAD_REQUEST);
        }
        String encodedPassword = passwordEncoder.encode(password);
        Member member = new Member();
        member.setEmail(email);
        member.setUsername(username);
        member.setPassword(encodedPassword);

        return memberRepository.save(member);
    }
}
