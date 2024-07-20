package com.yanoos.member.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostKeywordIn {
    private String keyword;
    private Long memberId;
}
