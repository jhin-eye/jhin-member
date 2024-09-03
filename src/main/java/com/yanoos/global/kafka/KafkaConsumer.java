package com.yanoos.global.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanoos.global.kafka.dto.PostCreatedIn;
import com.yanoos.member.service.business_service.event.EventBusinessService;
import com.yanoos.member.service.business_service.event.dto.OutBoxFindPostContainingKeywordsIn;
import com.yanoos.member.service.business_service.member.MemberBusinessService;
import com.yanoos.global.entity.member.Member;
import com.yanoos.global.entity.board.Post;
import com.yanoos.member.service.entity_service.post.PostEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final MemberBusinessService memberBusinessService;
    private final EventBusinessService eventBusinessService;
    private final PostEntityService postEntityService;
    /**
     * 카프카 메시지를 소비하는 메서드
     * POST_CREATED 토픽을 구독하고, FIND_KEYWORD_POST 이벤트를 outbox 한다.
     * 예외발생시 미소비 확인
     *
     * @param message
     * @throws JsonProcessingException
     */
    // @KafkaListener(topics = "POST_CREATED", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) throws JsonProcessingException {
        // log.info("Consumed message: {}", message);
        // ObjectMapper objectMapper = new ObjectMapper();
        // PostCreatedIn postCreatedIn = objectMapper.readValue(message, PostCreatedIn.class);
        // OutBoxFindPostContainingKeywordsIn outBoxFindPostContainingKeywordsIn = memberBusinessService.mapMembersWithPost(postCreatedIn);//멤버 포스트 매핑
        // outBoxFindPostContainingKeywordsIn.setParentEventId(postCreatedIn.getEventId());
        //
        // eventBusinessService.outBoxFindKeywordPost(outBoxFindPostContainingKeywordsIn);
        // log.info("consume finish");
    }

    private OutBoxFindPostContainingKeywordsIn createOutBoxFindPostContainingKeywords(List<Member> members, Post post) {
        return OutBoxFindPostContainingKeywordsIn.builder()
                .members(members)
                .postId(post.getId())
                .build();
    }


}
