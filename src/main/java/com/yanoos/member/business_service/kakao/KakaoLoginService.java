package com.yanoos.member.business_service.kakao;

import com.yanoos.global.jwt.service.JwtTokenService;
import com.yanoos.global.util.WebClientService;
import com.yanoos.global.util.dto.KakaoTokenResDTO;
import com.yanoos.member.controller.dto.KakaoUser;
import com.yanoos.global.jwt.dto.MyJwtDTO;
import com.yanoos.member.entity.Member;
import com.yanoos.member.entity.MemberOAuthKakao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class KakaoLoginService {
    private final WebClientService webClientService;
    private final KakaoMemberService kakaoMemberService;
    private final JwtTokenService jwtTokenService;
    @Value("${kakao.client-id}")
    private String KAKAO_API_KEY;
    @Value("${kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URI;
    @Value("${kakao.client-secret}")
    private String KAKAO_CLIENT_SECRET;
    public void setUrlEnvironment(Model model){
        model.addAttribute("clientId", KAKAO_API_KEY);
        model.addAttribute("redirectUri", KAKAO_REDIRECT_URI);
    }


    @Transactional
    public MyJwtDTO getUserInfoByReceivedAuthorizationCode(Map<String, String> params) {
        String authorizationCode =  params.get("code");
        log.info("authorizationCode = {}", authorizationCode);

        //카카오 토큰 요청
        KakaoTokenResDTO kakaoTokenResDTO = webClientService.requestKakaoToken(authorizationCode,KAKAO_API_KEY, KAKAO_CLIENT_SECRET, KAKAO_REDIRECT_URI).block();
        log.info("tokenDTO = {}",kakaoTokenResDTO);

        //카카오 토큰으로 유저정보 가져오기
        String kakaoAccessToken = kakaoTokenResDTO.getAccess_token();
        KakaoUser kakaoUser = webClientService.requestKakaoUserInfo(kakaoAccessToken).block();
        log.info("kakao user = {}",kakaoUser);

        //TODO: 기존회원이 아니라면 가입 로직 처리
        //기존회원인지 판단
        MemberOAuthKakao memberOAuthKakao = getKakaoMember(kakaoUser);
        log.info("kakao member result = {}", memberOAuthKakao);
        //todo: jwt화
        Member member = memberOAuthKakao.getMemberOAuth().getMember();
        Long memberId = member.getMemberId();
        String accessToken = jwtTokenService.generateAccessToken(memberId);
        String refreshToken = jwtTokenService.generateRefreshToken(memberId);

        return MyJwtDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private MemberOAuthKakao getKakaoMember(KakaoUser kakaoUser) {
        String kakaoId = Objects.requireNonNull(kakaoUser).getId();

        Optional<MemberOAuthKakao> byKakaoUserId = kakaoMemberService.findByKakaoUserId(kakaoId);
        return byKakaoUserId.orElseGet(() -> kakaoMemberService.joinKakaoMember(kakaoUser));

    }
}
