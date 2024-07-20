package com.yanoos.member.business_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateKeywordIn {
    private String keyword;
}
