package com.yanoos.global.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanoos.global.kafka.dto.PostCreatedIn;
import com.yanoos.member.service.business_service.event.EventBusinessService;
import com.yanoos.member.service.business_service.event.dto.OutBoxFindKeywordPostIn;
import com.yanoos.member.service.business_service.member.MemberBusinessService;
import com.yanoos.global.entity.member.Member;
import com.yanoos.member.service.entity_service.post.PostEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    @KafkaListener(topics = "NEW_POST", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) throws JsonProcessingException {
        log.info("Consumed message: {}", message);
        ObjectMapper objectMapper = new ObjectMapper();
        PostCreatedIn postCreatedIn = objectMapper.readValue(message, PostCreatedIn.class);

        //post memeber mapping
        Map<Member, List<String>> memberListMap = memberBusinessService.mapMembersWithPost(postCreatedIn);
        //alert 필요한 member들과 그들의 일치 키워드 제공
        OutBoxFindKeywordPostIn outBoxFindKeywordPostIn = OutBoxFindKeywordPostIn.from(memberListMap, postCreatedIn);

        eventBusinessService.outBoxFindKeywordPost(outBoxFindKeywordPostIn);
        log.info("consume finish");
        // ack.acknowledge();
    }

    // private OutBoxFindPostContainingKeywordsIn createOutBoxFindPostContainingKeywords(List<Member> members, Post post) {
    //     return OutBoxFindPostContainingKeywordsIn.builder()
    //             .members(members)
    //             .postId(post.getId())
    //             .build();
    // }


}
