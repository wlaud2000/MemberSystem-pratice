package com.study.member.service;

import com.study.member.dto.MemberDTO;
import com.study.member.entity.MemberEntity;
import com.study.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        //1.dto → entity
        //2.repository의 save 메소드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        //repository의 save메소드 호출 (조건, entity객체를 넘겨줘야함.)
    }
}
