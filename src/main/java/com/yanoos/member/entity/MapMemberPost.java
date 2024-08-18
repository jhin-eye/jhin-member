package com.yanoos.member.entity;

import com.yanoos.member.entity.dto.MapMemberKeywordId;
import com.yanoos.member.entity.dto.MapMemberPostId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "map_member_post")
public class MapMemberPost {

    @EmbeddedId
    private MapMemberPostId id;

    @ManyToOne
    @MapsId("memberId")
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private boolean checked;

    // 빌더 패턴을 사용하여 인스턴스를 생성할 때 id를 설정하는 메서드
    @Builder
    public MapMemberPost(Member member, Post post) {
        this.id = new MapMemberPostId(member.getMemberId(), post.getPostId());
        this.member = member;
        this.post = post;
    }
}