package com.yanoos.member.business_service.kakao;

import com.yanoos.member.controller.dto.KakaoUser;
import com.yanoos.member.entity.Member;
import com.yanoos.member.entity.MemberOAuth;
import com.yanoos.member.entity.MemberOAuthKakao;
import com.yanoos.member.repository.memberOAuthKakao.MemberOAuthKakaoRepository;
import com.yanoos.member.repository.member_OAuth.MemberOAuthRepository;
import com.yanoos.member.repository.member.MemberRepository;
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
    private final MemberOAuthKakaoRepository memberOAuthKakaoRepository;
    private final MemberOAuthRepository memberOAuthRepository;
    private final MemberRepository memberRepository;
    public Optional<MemberOAuthKakao> findByKakaoUserId(String kakaoUserId) {
        return memberOAuthKakaoRepository.findByKakaoId(kakaoUserId);
    }

    @Transactional
    public MemberOAuthKakao joinKakaoMember(KakaoUser kakaoUser) {
        String kakaoUserNickname = kakaoUser.getKakaoAccount().getProfile().getNickname();
        String kakaoUserEmail = kakaoUser.getKakaoAccount().getEmail();

        Member member = Member.builder()
                .memberEmail(kakaoUserEmail)
                .memberNickname(kakaoUserNickname)
                .build();
        memberRepository.save(member);

        MemberOAuth memberOAuth = MemberOAuth.builder()
                .member(member)
                .oauthHost(KAKAO_HOST_NAME)
                .build();
        memberOAuthRepository.save(memberOAuth);

        return memberOAuthKakaoRepository.save(
                MemberOAuthKakao.builder()
                        .kakaoId(kakaoUser.getId())
                        .kakaoNickname(kakaoUserNickname)
                        .kakaoEmail(kakaoUserEmail)
                        .memberOAuth(memberOAuth)
                        .build()
        );
    }
}
