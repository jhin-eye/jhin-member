package com.yanoos.member.business_service.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yanoos.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OutBoxFindPostContainingKeywordsIn {
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
