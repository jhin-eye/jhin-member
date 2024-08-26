package com.yanoos.member.service.business_service.event.dto;

import com.yanoos.global.entity.member.Member;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OutBoxFindPostContainingKeywordsIn {
    @Setter
    private Long parentEventId;

    List<Member> members = new ArrayList<>();

    private Long postId;

    private String boardNameEng;

    private String boardNameKor;

    private String postNo;

    private String postTitle;

    private String postUrl;

    private String postWriteDate;
    private List<String> containKeywords;
}
