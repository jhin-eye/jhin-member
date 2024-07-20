package com.yanoos.member.entity_service.member;

import com.yanoos.global.exception.BusinessException;
import com.yanoos.global.exception.code.MemberErrorCode;
import com.yanoos.member.entity.Member;
import com.yanoos.member.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional(readOnly = true)
public class MemberEntityService {
    private final MemberRepository memberRepository;

    public Member getMemberByMemberId(long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(()->new BusinessException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
