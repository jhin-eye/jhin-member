package com.yanoos.member.business_service.keyword;

import com.yanoos.member.business_service.dto.CreateKeywordIn;
import com.yanoos.member.controller.dto.PostKeywordIn;
import com.yanoos.member.controller.dto.PostKeywordOut;
import com.yanoos.member.entity.Keyword;
import com.yanoos.member.entity.MapMemberKeyword;
import com.yanoos.member.entity.Member;
import com.yanoos.member.entity_service.keyword.KeywordEntityService;
import com.yanoos.member.entity_service.map_member_keyword.MapMemberKeywordEntityService;
import com.yanoos.member.entity_service.map_member_keyword.dto.CreateMapMemberKeywordIn;
import com.yanoos.member.entity_service.member.MemberEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class KeywordBusinessService {
    private final KeywordEntityService keywordEntityService;
    private final MapMemberKeywordEntityService mapMemberKeywordEntityService;
    private final MemberEntityService memberEntityService;

    /**
     * 키워드 등록 및 등록 멤버에 키워드 매핑
     *
     * @param postKeywordIn
     * @return
     */
    @Transactional
    public PostKeywordOut postKeyword(PostKeywordIn postKeywordIn) {
        Long memberId = postKeywordIn.getMemberId();

        Member member = memberEntityService.getMemberByMemberId(memberId);
        Keyword keyword = keywordEntityService.createKeyword(CreateKeywordIn.builder()
                .keyword(postKeywordIn.getKeyword())
                .build());

        MapMemberKeyword mapMemberKeyword = mapMemberKeywordEntityService.createMapMemberKeyword(CreateMapMemberKeywordIn.builder()
                .keyword(keyword)
                .member(member)
                .build());

        return PostKeywordOut.builder()
                .isSuccess(true)
                .build();
    }

    public List<String> getKeywordsByMemberId(long memberId) {
        List<Keyword> mapMemberKeywords = keywordEntityService.getKeywordsByMemberId(memberId);
        return mapMemberKeywords.stream().map(Keyword::getKeyword).toList();
    }
}
