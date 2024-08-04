package com.yanoos.member.repository.map_member_keyword;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yanoos.member.entity.MapMemberKeyword;
import com.yanoos.member.entity.QMapMemberKeyword;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MapMemberKeywordRepositoryCustomImpl implements MapMemberKeywordRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MapMemberKeyword> findByKeywordIds(List<Long> keywordIds) {
        QMapMemberKeyword mapMemberKeyword = QMapMemberKeyword.mapMemberKeyword;
        return queryFactory.selectFrom(mapMemberKeyword)
                .where(mapMemberKeyword.keyword.keywordId.in(keywordIds))
                .fetch();
    }
}
