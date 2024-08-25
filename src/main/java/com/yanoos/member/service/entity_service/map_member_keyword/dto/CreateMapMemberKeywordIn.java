package com.yanoos.member.service.entity_service.map_member_keyword.dto;

import com.yanoos.member.entity.member.Keyword;
import com.yanoos.member.entity.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMapMemberKeywordIn {
    private Keyword keyword;
    private Member member;

}
