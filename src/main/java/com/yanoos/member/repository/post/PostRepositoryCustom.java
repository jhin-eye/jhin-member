package com.yanoos.member.repository.post;

import com.yanoos.member.entity.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    //select with member id
    public List<Post> findByMemberId(Long memberId, Pageable pageable);
}
