package com.yanoos.member.entity_service.map_member_post;

import com.yanoos.member.entity.MapMemberPost;
import com.yanoos.member.repository.map_member_post.MapMemberPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MapMemberPostEntityService {
    private final MapMemberPostRepository mapMemberPostRepository;

    public List<MapMemberPost> getMapMemberPostsByMemberId(Long memberId) {
        return mapMemberPostRepository.findByMemberMemberIdOrderByMapMemberPostIdDesc(memberId);
    }

    public List<MapMemberPost> getMapMemberPostsByPostId(Long postId) {
        return mapMemberPostRepository.findByPostPostId(postId);
    }

    public MapMemberPost getByMapMemberPostId(Long mapMemberPostId) {
        return mapMemberPostRepository.findById(mapMemberPostId).orElseThrow();
    }

    public MapMemberPost saveMapMemberPost(MapMemberPost mapMemberPost) {
        mapMemberPostRepository.save(mapMemberPost);
        return mapMemberPost;
    }
}
