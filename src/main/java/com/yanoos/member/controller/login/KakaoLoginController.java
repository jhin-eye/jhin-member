package com.yanoos.member.controller.login;


import com.yanoos.global.util.dto.KakaoTokenResDTO;
import com.yanoos.member.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/kakao")
public class KakaoLoginController {
    private final KakaoLoginService kakaoLoginService;

    //카카오로그인 페이지
    @GetMapping("/login")
    public String getKakaoLoginPage(Model model){
        kakaoLoginService.setting(model);
        return "kakaoLoginForm";
    }

    //인가코드 받아서 카카오토큰 요청
    @GetMapping("/callback")
    @ResponseBody
    public KakaoTokenResDTO kakaoCallback(@RequestParam Map<String,String> params){
        return kakaoLoginService.handleCallBack(params);
    }
}
