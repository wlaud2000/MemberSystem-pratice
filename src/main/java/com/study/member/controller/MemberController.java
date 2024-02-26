package com.study.member.controller;

import com.study.member.dto.MemberDTO;
import com.study.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    private final MemberService memberService;

    //회원가입 페이지 출력 요청
    @GetMapping(value = "/member/save")
    public String saveForm(){

        return "save";
    }

    @PostMapping(value = "/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO){

        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping(value = "/member/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping(value = "/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {

        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null){
            //login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        } else {
            //login 실패
            return "login";
        }

    }
}
