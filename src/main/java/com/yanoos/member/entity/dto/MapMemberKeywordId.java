package com.yanoos.member.entity.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class MapMemberKeywordId implements Serializable {

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "keyword_id")
    private Long keywordId;

    // equals, hashCode
}