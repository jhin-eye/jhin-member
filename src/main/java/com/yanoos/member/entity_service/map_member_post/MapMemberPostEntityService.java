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

    public List<MapMemberPost> getMapMemberPostByMemberId(Long memberId) {
        return mapMemberPostRepository.findByMemberId(memberId);
    }

    public List<MapMemberPost> getMapMemberPostByPostId(Long postId) {
        return mapMemberPostRepository.findByPostId(postId);
    }
}
