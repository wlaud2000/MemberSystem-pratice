package com.study.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //기본 페이지 요청 메서드
    @GetMapping(value = "/")
    public String index() {

        return "index"; //index.html파일로 이동해라
    }
}
