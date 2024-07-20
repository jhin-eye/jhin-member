package com.yanoos.member.repository.keyword;

import com.yanoos.member.entity.Keyword;

import java.util.List;

public interface KeywordRepositoryCustom {
    List<Keyword> findKeywordsByMemberId(long memberId);
}
