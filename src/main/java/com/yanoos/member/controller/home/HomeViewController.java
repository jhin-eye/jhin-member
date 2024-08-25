package com.yanoos.member.controller.home;

import com.yanoos.global.jwt.TokenType;
import com.yanoos.global.jwt.service.JwtTokenService;
import com.yanoos.member.service.business_service.kakao.KakaoLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeViewController {
    private final JwtTokenService jwtTokenService;
    private final KakaoLoginService kakaoLoginService;
    @GetMapping()
    public String getHome(HttpServletRequest request, HttpServletResponse response, Model model){
        try{
            String jwt = jwtTokenService.getJwtFromRequest(request,TokenType.ACCESS);
            jwtTokenService.validateToken(jwt, TokenType.ACCESS, response);
            return "redirect:/api/view/settings/keyword";
        }catch (Exception e){
            kakaoLoginService.setLoginUrlEnvironment(model);
            return "index";
        }
    }
}
