package com.yanoos.member.controller.kakao;


import com.yanoos.global.jwt.dto.MyJwtDTO;
import com.yanoos.member.business_service.kakao.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/kakao")
public class KakaoLoginController {
    private final KakaoLoginService kakaoLoginService;

    //카카오로그인 페이지
    @GetMapping("/login")
    public String getKakaoLoginPage(Model model){
        kakaoLoginService.setUrlEnvironment(model);
        return "kakaoLoginForm";
    }

    //인가코드 받아서 카카오토큰 요청 -> 카카오토큰으로 유저 정보 가져오기
    @GetMapping("/callback")
    @ResponseBody
    public MyJwtDTO getUserInfoByReceivedAuthorizationCode(@RequestParam Map<String,String> params){
        MyJwtDTO userInfoByReceivedAuthorizationCode = kakaoLoginService.getUserInfoByReceivedAuthorizationCode(params);
        log.info("getUserInfoByReceivedAuthorizationCode success");
        return userInfoByReceivedAuthorizationCode;
    }
}
