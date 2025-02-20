package com.example.demo.controller;


import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.member.Member;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @Autowired
    public AuthController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
        authenticationService
                .register(registerRequest.username(), registerRequest.email(), registerRequest.password());
        return ResponseEntity.ok("Kullanıcı başarıyla oluşturuldu!");
    }
}
