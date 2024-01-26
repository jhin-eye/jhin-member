package com.yanoos.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {
    @Id @GeneratedValue
    private Long memberId;
    @Column(unique = true, nullable = false)
    private String memberEmail;
    @Column(unique = true, nullable = false)
    private String memberNickname;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OauthMember> oauthMembers;

}
