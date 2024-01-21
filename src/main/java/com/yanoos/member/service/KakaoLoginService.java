package com.yanoos.member.service;

import com.yanoos.global.exception.ApiException;
import com.yanoos.global.exception.code.TestErrorCode;
import com.yanoos.global.util.WebClientService;
import com.yanoos.global.util.dto.KakaoTokenResDTO;
import com.yanoos.member.controller.dto.KakaoUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Map;
@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class KakaoLoginService {
    private final WebClientService webClientService;

    @Value("${kakao.client-id}")
    private String KAKAO_API_KEY;
    @Value("${kakao.redirect-uri}")
    private String REDIRECT_URI;
    public void setting(Model model){
        model.addAttribute("clientId", KAKAO_API_KEY);
        model.addAttribute("redirectUri", REDIRECT_URI);
    }

    public KakaoTokenResDTO handleCallBack(Map<String, String> params) {
        if(params.containsKey("error")){
            throw new ApiException(TestErrorCode.TEST_ERROR);
        }
        String authorizationCode =  params.get("code");
        log.info("authorizationCode = {}", authorizationCode);

        //카카오 토큰 요청
        KakaoTokenResDTO kakaoTokenResDTO = webClientService.requestKakaoToken(authorizationCode).block();
        log.info("tokenDTO = {}",kakaoTokenResDTO);

        //카카오 토큰 해석
        String kakaoAccessToken = kakaoTokenResDTO.getAccess_token();
        KakaoUser kakaoUser = webClientService.requestKakaoUserInfo(kakaoAccessToken).block();
        log.info("kakao user = {}",kakaoUser);
        return kakaoTokenResDTO;
    }
}
