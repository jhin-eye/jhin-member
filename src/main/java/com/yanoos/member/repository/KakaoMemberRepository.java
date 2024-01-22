package com.yanoos.member.repository;

import com.yanoos.member.entity.KakaoMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KakaoMemberRepository extends JpaRepository<KakaoMember,Long>, KakaoMemberRepositoryCustom{
    Optional<KakaoMember> findByKakaoId(String kakaoUserId);
}
