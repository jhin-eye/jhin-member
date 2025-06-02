package com.yanoos.global.jwt;

import com.yanoos.global.entity.member.MemberRole;
import lombok.Data;
import java.io.Serializable;

@Data
public class CustomPrincipal implements Serializable {
    private Long memberId;
    private String role; // 사용자 역할 (예: USER, ADMIN 등)
}
