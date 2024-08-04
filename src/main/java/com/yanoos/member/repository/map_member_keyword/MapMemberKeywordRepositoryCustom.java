package com.yanoos.member.repository.map_member_keyword;

import com.yanoos.member.entity.Keyword;
import com.yanoos.member.entity.MapMemberKeyword;

import java.util.List;

public interface MapMemberKeywordRepositoryCustom {
    List<MapMemberKeyword> findByKeywordIds(List<Long> keywordIds);

}
