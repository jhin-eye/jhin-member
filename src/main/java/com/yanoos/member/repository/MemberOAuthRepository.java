package com.yanoos.member.repository;

import com.yanoos.member.entity.MemberOAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberOAuthRepository  extends JpaRepository<MemberOAuth,Long>{
}
