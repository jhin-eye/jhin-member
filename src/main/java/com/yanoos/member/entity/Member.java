package com.yanoos.member.entity;

import com.yanoos.member.controller.dto.MemberOut;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_email", nullable = false, unique = true)
    private String memberEmail;

    @Column(name = "member_nickname", nullable = false, unique = true)
    private String memberNickname;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemberOAuth> memberOAuths = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MapMemberKeyword> mapMemberKeywords = new ArrayList<>();

    @Column(name="telegram_authentication_uuid", unique = true)
    private UUID telegramAuthenticationUuid;

    @Column(name="telegram_authentication_uuid_created_at")
    private LocalDateTime telegramUuidCreatedAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MapMemberTelegramUser> mapMemberTelegramUsers = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MapMemberPost> mapMemberPosts = new ArrayList<>();

    public void generateTelegramUuid(UUID uuid) {
        this.telegramAuthenticationUuid = uuid;
        this.telegramUuidCreatedAt = LocalDateTime.now();
    }


    // Getter, Setter
    public MemberOut toDto(){
        return MemberOut.builder()
                .memberId(this.memberId)
                .memberEmail(this.memberEmail)
                .memberNickname(this.memberNickname)
                .build();
    }

    public void addMapMemberPost(MapMemberPost mapMemberPost) {
        this.mapMemberPosts.add(mapMemberPost);
    }
}