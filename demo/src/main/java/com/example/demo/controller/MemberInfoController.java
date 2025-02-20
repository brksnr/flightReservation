package com.example.demo.controller;

import com.example.demo.dto.MemberInfoRequest;
import com.example.demo.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info")
public class MemberInfoController {

    private final MemberInfoService memberInfoService;

    @Autowired
    public MemberInfoController(MemberInfoService memberInfoService) {
        this.memberInfoService = memberInfoService;
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateMemberInfo(@PathVariable Long id, @RequestBody MemberInfoRequest memberInfoRequest){
        memberInfoService.updateMemberInfo(id,memberInfoRequest);
        return ResponseEntity.ok("Bilgiler başarıyla güncellendi!");
    }
}
