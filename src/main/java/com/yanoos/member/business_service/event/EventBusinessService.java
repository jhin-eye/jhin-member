package com.yanoos.member.business_service.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yanoos.member.business_service.event.dto.OutBoxFindPostContainingKeywordsIn;
import com.yanoos.member.entity.Member;
import com.yanoos.member.entity.event.Event;
import com.yanoos.member.entity.event.EventType;
import com.yanoos.member.entity_service.event.EventEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventBusinessService {
    private final EventEntityService eventEntityService;


    /**
     * FIND_KEYWORD_POST 이벤트를 outbox 한다.
     * @param outBoxFindKeywordPostIn
     */
    @Transactional
    public void outBoxFindKeywordPost(OutBoxFindPostContainingKeywordsIn outBoxFindKeywordPostIn) {
        for(Member member : outBoxFindKeywordPostIn.getMembers()) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode jsonObject = objectMapper.createObjectNode();
            jsonObject.put("postNo", outBoxFindKeywordPostIn.getPostNo());
            jsonObject.put("postTitle", outBoxFindKeywordPostIn.getPostTitle());
            jsonObject.put("postUrl", outBoxFindKeywordPostIn.getPostUrl());
            jsonObject.put("postWriteDate", outBoxFindKeywordPostIn.getPostWriteDate());
            jsonObject.put("boardNameEng", outBoxFindKeywordPostIn.getBoardNameEng());
            jsonObject.put("boardNameKor", outBoxFindKeywordPostIn.getBoardNameKor());
            jsonObject.put("memberId", member.getMemberId());

            Event event = Event.builder()
                    .eventType(EventType.FIND_KEYWORD_POST.name())
                    .eventData(jsonObject.toString())
                    .published(false)
                    .build();
            eventEntityService.save(event);
        }
    }
}
