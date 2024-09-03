package com.yanoos.member.service.business_service.dto;

import com.yanoos.global.entity.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateKeywordIn {
    private Member member;

    private String keyword;
}
