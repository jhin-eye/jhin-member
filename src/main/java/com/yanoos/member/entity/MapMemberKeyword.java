package com.yanoos.member.entity;

import com.yanoos.member.entity.dto.MapMemberKeywordId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


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

    // 빌더 패턴을 사용하여 인스턴스를 생성할 때 id를 설정하는 메서드
    @Builder
    public MapMemberKeyword (Member member, Keyword keyword) {
        this.id = new MapMemberKeywordId(member.getMemberId(), keyword.getKeywordId());
        this.member = member;
        this.keyword = keyword;

    }
}