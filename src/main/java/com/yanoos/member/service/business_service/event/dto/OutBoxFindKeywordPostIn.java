package com.yanoos.member.service.business_service.event.dto;

import com.yanoos.global.entity.member.Member;
import com.yanoos.global.kafka.dto.PostCreatedIn;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OutBoxFindKeywordPostIn {
    @Setter
    private Long parentEventId;

    List<Member> members = new ArrayList<>();

    private Long postId;


    public static OutBoxFindKeywordPostIn from(Map<Member, List<String>> memberKeywords, PostCreatedIn postCreatedIn) {
        return OutBoxFindKeywordPostIn.builder()
                .members(new ArrayList<>(memberKeywords.keySet()))
                .postId(postCreatedIn.getValue().getPostId())
                .parentEventId(postCreatedIn.getParentEventId())
                .build();
    }
}
