package com.yanoos.member.business_service.member;

import com.yanoos.member.controller.member.GenerateTelegramUuidOut;
import com.yanoos.member.entity_service.member.MemberEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberBusinessService {
    private final MemberEntityService memberEntityService;

    /**
     * 텔레그램 UUID를 생성한다.
     * @param memberId
     * @return
     */
    @Transactional
    public GenerateTelegramUuidOut generateTelegramUuid(Long memberId) {
        UUID uuid = UUID.randomUUID();
        memberEntityService.updateTelegramUuid(memberId, uuid);
        return GenerateTelegramUuidOut.builder()
                .memberId(memberId)
                .uuid(uuid)
                .build();
    }
}
