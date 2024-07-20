package com.yanoos.member.entity.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class MapMemberKeywordId implements Serializable {

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "keyword_id")
    private Long keywordId;
}