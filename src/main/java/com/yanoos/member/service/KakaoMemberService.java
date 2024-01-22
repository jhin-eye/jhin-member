package com.yanoos.member.service;

import com.yanoos.global.exception.BusinessException;
import com.yanoos.global.exception.code.KakaoErrorCode;
import com.yanoos.member.controller.dto.KakaoUser;
import com.yanoos.member.entity.KakaoMember;
import com.yanoos.member.entity.Member;
import com.yanoos.member.repository.KakaoMemberRepository;
import com.yanoos.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class KakaoMemberService {
    @Value("${kakao.host-name}")
    private String KAKAO_HOST_NAME;
    private final KakaoMemberRepository kakaoMemberRepository;
    private final MemberRepository memberRepository;
    public Optional<KakaoMember> findByKakaoUserId(String kakaoUserId) {
        return kakaoMemberRepository.findByKakaoId(kakaoUserId);
    }

    @Transactional
    public KakaoMember joinKakaoMember(KakaoUser kakaoUser) {
        String kakaoUserNickname = kakaoUser.getKakaoAccount().getProfile().getNickname();
        String kakaoUserEmail = kakaoUser.getKakaoAccount().getEmail();

        Member member = Member.builder()
                .memberEmail(kakaoUserEmail)
                .memberNickname(kakaoUserNickname)
                .build();
        memberRepository.save(member);

        return kakaoMemberRepository.save(
                KakaoMember.builder()
                        .kakaoId(kakaoUser.getId())
                        .kakaoNickname(kakaoUserNickname)
                        .kakaoEmail(kakaoUserEmail)
                        .oauthHost(KAKAO_HOST_NAME)
                        .member(member)
                        .build()
        );
    }
}
