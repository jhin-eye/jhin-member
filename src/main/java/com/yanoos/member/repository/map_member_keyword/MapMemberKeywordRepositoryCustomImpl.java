package com.yanoos.member.repository.map_member_keyword;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MapMemberKeywordRepositoryCustomImpl implements MapMemberKeywordRepositoryCustom{
    private final JPAQueryFactory queryFactory;

}
