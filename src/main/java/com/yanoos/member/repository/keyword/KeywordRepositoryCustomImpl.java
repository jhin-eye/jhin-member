package com.yanoos.member.repository.keyword;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yanoos.member.entity.Keyword;
import com.yanoos.member.entity.QKeyword;
import com.yanoos.member.entity.QMapMemberKeyword;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public List<Keyword> findKeywordsInPostTitle(String postTitle) {
        QKeyword keyword = QKeyword.keyword1;
        List<Keyword> allKeywords = queryFactory.selectFrom(keyword).fetch();
        return allKeywords.stream()
                .filter(k -> postTitle.contains(k.getKeyword()))
                .collect(Collectors.toList());
    }
}
