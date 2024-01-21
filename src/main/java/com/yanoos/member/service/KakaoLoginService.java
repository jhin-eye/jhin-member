package com.yanoos.member.service;

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
    @Value("${kakao.client-secret}")
    private String CLIENT_SECRET;
    public void setUrlEnvironment(Model model){
        model.addAttribute("clientId", KAKAO_API_KEY);
        model.addAttribute("redirectUri", REDIRECT_URI);
    }

    public KakaoUser getUserInfoByReceivedAuthorizationCode(Map<String, String> params) {
        String authorizationCode =  params.get("code");
        log.info("authorizationCode = {}", authorizationCode);

        //카카오 토큰 요청
        KakaoTokenResDTO kakaoTokenResDTO = webClientService.requestKakaoToken(authorizationCode,KAKAO_API_KEY,CLIENT_SECRET,REDIRECT_URI).block();
        log.info("tokenDTO = {}",kakaoTokenResDTO);

        //카카오 토큰으로 유저정보 가져오기
        String kakaoAccessToken = kakaoTokenResDTO.getAccess_token();
        KakaoUser kakaoUser = webClientService.requestKakaoUserInfo(kakaoAccessToken).block();
        log.info("kakao user = {}",kakaoUser);
        return kakaoUser;
    }
}
