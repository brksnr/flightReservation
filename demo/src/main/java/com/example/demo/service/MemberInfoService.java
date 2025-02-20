package com.example.demo.service;



import com.example.demo.dto.MemberInfoRequest;
import com.example.demo.entity.member.Member;
import com.example.demo.entity.member.MemberInfo;
import com.example.demo.exceptions.ApiException;
import com.example.demo.repository.MemberInfoRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MemberInfoService {

    private final MemberRepository memberRepository;
    private final MemberInfoRepository memberInfoRepository;

    @Autowired
    public MemberInfoService(MemberRepository memberRepository, MemberInfoRepository memberInfoRepository) {
        this.memberRepository = memberRepository;
        this.memberInfoRepository = memberInfoRepository;
    }

    public MemberInfo updateMemberInfo(Long id, MemberInfoRequest memberInfoRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ApiException("Kullanıcı bulunamadı!", HttpStatus.NOT_FOUND);
                });
        MemberInfo memberInfo = member.getMemberInfo();
        if (memberInfo == null) {
            memberInfo = new MemberInfo();
            member.setMemberInfo(memberInfo);
            memberInfo.setMember(member);
        }

        memberInfo.setFullName(memberInfoRequest.fullName());
        memberInfo.setTelephone(memberInfoRequest.telephone());
        memberInfo.setBirthdate(memberInfoRequest.birthDate());
        memberInfo.setHeight(memberInfoRequest.height());
        memberInfo.setWeight(memberInfoRequest.weight());

        member.setMemberInfo(memberInfo);
        return memberInfoRepository.save(memberInfo);
    }
}
