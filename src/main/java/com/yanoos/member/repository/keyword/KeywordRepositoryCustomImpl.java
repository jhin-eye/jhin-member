package com.yanoos.member.repository.keyword;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yanoos.member.entity.Keyword;
import com.yanoos.member.entity.QKeyword;
import com.yanoos.member.entity.QMapMemberKeyword;
import lombok.RequiredArgsConstructor;

import java.util.List;



@RequiredArgsConstructor
public class KeywordRepositoryCustomImpl implements KeywordRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Keyword> findKeywordsByMemberId(long memberId) {
        QMapMemberKeyword mapMemberKeyword = QMapMemberKeyword.mapMemberKeyword;
        QKeyword keyword = QKeyword.keyword1;

        return queryFactory.selectFrom(keyword)
                .innerJoin(mapMemberKeyword).on(mapMemberKeyword.keyword.eq(keyword))
                .where(mapMemberKeyword.member.memberId.eq(memberId))
                .fetch();
    }
}
