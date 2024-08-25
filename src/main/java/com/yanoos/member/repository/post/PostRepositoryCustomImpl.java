package com.yanoos.member.repository.post;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yanoos.member.entity.board.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.yanoos.member.entity.QMember.member;


@Repository
@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Post> findByMemberId(Long memberId, Pageable pageable) {
        return List.of();
    }
}
