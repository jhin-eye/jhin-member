package com.yanoos.member.repository.keyword;

import com.yanoos.member.entity.member.Keyword;

import java.util.List;

public interface KeywordRepositoryCustom {
    List<Keyword> findKeywordsByMemberId(long memberId);
    List<Keyword> findKeywordsInPostTitle(String postTitle);

}
