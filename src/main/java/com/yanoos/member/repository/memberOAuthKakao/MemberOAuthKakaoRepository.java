package com.yanoos.member.repository.memberOAuthKakao;

import com.yanoos.member.entity.MemberOAuthKakao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberOAuthKakaoRepository extends JpaRepository<MemberOAuthKakao,Long>, MemberOAuthKakaoRepositoryCustom{
    Optional<MemberOAuthKakao> findByKakaoId(String kakaoUserId);
}
