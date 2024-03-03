package com.study.member.service;

import com.study.member.dto.MemberDTO;
import com.study.member.entity.MemberEntity;
import com.study.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public MemberDTO login(MemberDTO memberDTO) {
        /*
            1. 회원이 이메일로 입력한 값을 DB에서 조회함.
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단.
            (원시적인 방법임. 이렇게 실제로 사용하면 큰일남.)
         */
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (byMemberEmail.isPresent()){
            //조회 결과가 있다. (해당 이메일을 가진 회원정보가 있다.)
            MemberEntity memberEntity = byMemberEmail.get(); //get은 포장을 한번 벗겨서 안에 있는 Entitiy를 가져올 수 있게 해줌.
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){ // entity와 dto가 같은지 함 보자., ==사용하면 안된다.equals라는 메소드를 사용해야함.
                //비밀번호가 일치
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                //비밀번호가 불일치(로그인 실패)
                return null;
            }
        } else {
            //조회 결과가 없다. (해당 이메일을 가진 회원이 없다.)
            return null;
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity: memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
            //MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            //memberDTOList.add(memberDTO); // 위에 한줄이랑 아래 두줄이랑 같은 의미
        }
        return memberDTOList;
    }
}
