package com.yanoos.member.controller.kakao;


import com.yanoos.global.jwt.TokenType;
import com.yanoos.global.jwt.dto.MyJwtDTO;
import com.yanoos.global.jwt.service.JwtTokenService;
import com.yanoos.member.business_service.kakao.KakaoLoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
@RequestMapping("/api/kakao")
public class KakaoLoginController {
    @Value("${jwt.access-token-expiration-time}")
    private int ACCESS_TOKEN_EXPIRATION_TIME;
    @Value("${jwt.refresh-token-expiration-time}")
    private int REFRESH_TOKEN_EXPIRATION_TIME;
    private final KakaoLoginService kakaoLoginService;
    private final JwtTokenService jwtTokenService;

    //카카오로그인 페이지
    @GetMapping("/login")
    public String getKakaoLoginPage(Model model) {
        kakaoLoginService.setUrlEnvironment(model);
        return "kakaoLoginForm";
    }

    //인가코드 받아서 카카오토큰 요청 -> 카카오토큰으로 유저 정보 가져오기
    @GetMapping("/callback")
    public String getUserInfoByReceivedAuthorizationCode(@RequestParam Map<String, String> params, HttpServletResponse response) {
        MyJwtDTO userInfoByReceivedAuthorizationCode = kakaoLoginService.getUserInfoByReceivedAuthorizationCode(params);
        log.info("getUserInfoByReceivedAuthorizationCode success");
        String accessToken = userInfoByReceivedAuthorizationCode.getAccessToken();
        String refreshToken = userInfoByReceivedAuthorizationCode.getRefreshToken();

        //JWT 토큰을 쿠키에 설정
        jwtTokenService.setTokensOnCookie(response,accessToken,refreshToken);
        return "kakaoCallback";
    }
}
