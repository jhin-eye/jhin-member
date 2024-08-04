package com.yanoos.global.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanoos.global.kafka.dto.PostCreatedIn;
import com.yanoos.member.business_service.event.EventBusinessService;
import com.yanoos.member.business_service.event.dto.OutBoxFindPostContainingKeywordsIn;
import com.yanoos.member.business_service.member.MemberBusinessService;
import com.yanoos.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final MemberBusinessService memberBusinessService;
    private final EventBusinessService eventBusinessService;

    /**
     * 카프카 메시지를 소비하는 메서드
     * POST_CREATED 토픽을 구독하고, FIND_KEYWORD_POST 이벤트를 outbox 한다.
     * 예외발생시 미소비 확인
     *
     * @param message
     * @throws JsonProcessingException
     */
    @KafkaListener(topics = "POST_CREATED", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) throws JsonProcessingException {
        log.info("Consumed message: {}", message);
        // 1. 메시지에 맞는 DTO 형식 작성
        // 2. 메시지를 DTO로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        PostCreatedIn postCreatedIn = objectMapper.readValue(message, PostCreatedIn.class);
        // 3. 변환된 DTO를 이용해 메시지 전송
        log.info("postCreatedIn: {}", postCreatedIn.toString());
        List<Member> members = memberBusinessService.findMembersByPostTitle(postCreatedIn.getValue().getPostTitle());
        OutBoxFindPostContainingKeywordsIn outBoxFindPostContainingKeywordsIn = createOutBoxFindPostContainingKeywords(members, postCreatedIn);
        eventBusinessService.outBoxFindKeywordPost(outBoxFindPostContainingKeywordsIn);

        // Scanner sc = new Scanner(System.in);
        // System.out.print("Enter your name: ");
        // if (sc.nextLine().equals("1")) {
        //     throw new RuntimeException();
        // }
        log.info("consume finish");
    }

    private OutBoxFindPostContainingKeywordsIn createOutBoxFindPostContainingKeywords(List<Member> members, PostCreatedIn postCreatedIn) {
        return OutBoxFindPostContainingKeywordsIn.builder()
                .members(members)
                .postNo(postCreatedIn.getValue().getPostNo())
                .postTitle(postCreatedIn.getValue().getPostTitle())
                .postUrl(postCreatedIn.getValue().getPostUrl())
                .postWriteDate(postCreatedIn.getValue().getPostWriteDate())
                .boardNameEng(postCreatedIn.getValue().getBoardNameEng())
                .boardNameKor(postCreatedIn.getValue().getBoardNameKor())
                .build();
    }


}
