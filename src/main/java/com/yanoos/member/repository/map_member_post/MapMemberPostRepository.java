package com.yanoos.member.repository.map_member_post;

import com.yanoos.member.entity.MapMemberPost;
import com.yanoos.member.entity.dto.MapMemberPostId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MapMemberPostRepository extends JpaRepository<MapMemberPost, MapMemberPostId> {
    public List<MapMemberPost> findByMemberId(Long memberId);
    public List<MapMemberPost> findByPostId(Long memberId);

}
