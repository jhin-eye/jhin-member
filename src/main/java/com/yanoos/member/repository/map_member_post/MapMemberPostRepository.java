package com.yanoos.member.repository.map_member_post;

import com.yanoos.member.entity.MapMemberPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapMemberPostRepository extends JpaRepository<MapMemberPost, Long> {
    public List<MapMemberPost> findByMemberMemberIdOrderByMapMemberPostIdDesc(Long memberId);

    public List<MapMemberPost> findByPostPostId(Long memberId);

}
