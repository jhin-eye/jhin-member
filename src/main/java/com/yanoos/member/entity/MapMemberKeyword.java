package com.yanoos.member.entity;

import com.yanoos.member.entity.dto.MapMemberKeywordId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "map_member_keyword")
public class MapMemberKeyword {

    @EmbeddedId
    private MapMemberKeywordId id;

    @ManyToOne
    @MapsId("memberId")
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @MapsId("keywordId")
    @JoinColumn(name = "keyword_id", nullable = false)
    private Keyword keyword;

    // Getter, Setter
}