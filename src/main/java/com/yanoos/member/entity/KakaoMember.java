package com.yanoos.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Data
@Table(name = "kakao_user")
public class KakaoMember extends OauthMember {
    protected KakaoMember(){}
    @Column(unique = true, nullable = false)
    private String kakaoId;
    @Column(unique = true, nullable = false)
    private String kakaoEmail;
    @Column( unique = false, nullable = false)
    private String kakaoNickname;



}
