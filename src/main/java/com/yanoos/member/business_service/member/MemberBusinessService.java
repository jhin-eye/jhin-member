package com.yanoos.member.business_service.member;

import com.yanoos.member.controller.member.GenerateTelegramUuidOut;
import com.yanoos.member.entity.Keyword;
import com.yanoos.member.entity.MapMemberKeyword;
import com.yanoos.member.entity.Member;
import com.yanoos.member.entity_service.keyword.KeywordEntityService;
import com.yanoos.member.entity_service.map_member_keyword.MapMemberKeywordEntityService;
import com.yanoos.member.entity_service.member.MemberEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberBusinessService {
    private final MemberEntityService memberEntityService;
    private final KeywordEntityService keywordEntityService;
    private final MapMemberKeywordEntityService mapMemberKeywordEntityService;

    /**
     * 텔레그램 UUID를 생성한다.
     *
     * @param memberId
     * @return
     */
    @Transactional
    public GenerateTelegramUuidOut generateTelegramUuid(Long memberId) {
        UUID uuid = UUID.randomUUID();
        memberEntityService.updateTelegramUuid(memberId, uuid);
        return GenerateTelegramUuidOut.builder()
                .memberId(memberId)
                .uuid(uuid)
                .build();
    }

    /**
     * postTitle에 포함된 키워드를 모니터하는 회원 목록을 조회한다.
     * @param postTitle
     * @return
     */
    public List<Member> findMembersByPostTitle(String postTitle) {

        //2. postTitle에 포함된 키워드 목록 작성
        List<Keyword> keywords = keywordEntityService.getKeywordsInPostTitle(postTitle);
        log.info("keywords which are in post title: {}", keywords);
        //3. 해당 키워드를 모니터하는 회원 목록 작성
        List<MapMemberKeyword> mapMemberKeywords = mapMemberKeywordEntityService.getMapMemberKeywordsByKeywordIds(keywords.stream().map(Keyword::getKeywordId).toList());
        List<Member> members = mapMemberKeywords.stream().map(MapMemberKeyword::getMember).toList();
        //members 중복 제거
        members = members.stream().distinct().toList();

        //4. 3에서 작성한 회원 목록 리턴
        return members;

    }
}
