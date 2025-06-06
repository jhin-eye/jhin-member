package com.yanoos.member.service.business_service.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yanoos.member.service.business_service.event.dto.OutBoxFindKeywordPostIn;
import com.yanoos.global.entity.member.Member;
import com.yanoos.global.entity.event.Event;
import com.yanoos.global.entity.event.EventType;
import com.yanoos.member.service.entity_service.event.EventEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;
import java.time.ZoneId;

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
    public void outBoxFindKeywordPost(OutBoxFindKeywordPostIn outBoxFindKeywordPostIn) {
        for(Member member : outBoxFindKeywordPostIn.getMembers()) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode jsonObject = objectMapper.createObjectNode();
            jsonObject.put("memberId", member.getId());
            jsonObject.put("postId", outBoxFindKeywordPostIn.getPostId());

            Event event = Event.builder()
                    .parentEventId(outBoxFindKeywordPostIn.getParentEventId())
                    .eventType(EventType.FIND_KEYWORD_POST.name())
                    .eventData(jsonObject.toString())
                    .published(false)
                    .createdAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")))
                    .tryCount(0L)
                    .build();
            eventEntityService.save(event);
        }
    }
}
