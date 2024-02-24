package com.yanoos.member.entity;

import com.yanoos.global.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "oauth_user")
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
public class OauthMember {
    protected OauthMember(){}
    @Id
    @GeneratedValue
    private Long oauthId;
    private String oauthHost;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
