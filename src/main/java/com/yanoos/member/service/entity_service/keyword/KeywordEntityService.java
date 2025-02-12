package com.yanoos.member.service.entity_service.keyword;

import com.yanoos.global.entity.member.Keyword;
import com.yanoos.global.exception.BusinessException;
import com.yanoos.global.exception.code.KeywordErrorCode;
import com.yanoos.member.repository.keyword.KeywordRepository;
import com.yanoos.member.service.business_service.dto.CreateKeywordIn;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class KeywordEntityService {
    private final KeywordRepository keywordRepository;

    @Transactional
    public Keyword createKeyword(CreateKeywordIn createKeywordin) {
        Optional<Keyword> optionalKeyword = keywordRepository.findByKeyword(createKeywordin.getKeyword());


        Keyword keyword;
        keyword = optionalKeyword.orElseGet(() -> keywordRepository.save(Keyword.builder()
                .keyword(createKeywordin.getKeyword())
                .build()));

        return keyword;
    }


    public Keyword getKeywordByKeywordId(Long keywordId) {
        return keywordRepository.findById(keywordId).orElseThrow(()-> new BusinessException(KeywordErrorCode.KEYWORD_NOT_FOUND));
    }

    public void verifyKeywordOwnership(Long memberId, Long keywordId) {
        Keyword keyword = getKeywordByKeywordId(keywordId);

        if(!Objects.equals(keyword.getMember().getId(), memberId)){
            throw new BusinessException(KeywordErrorCode.FORBIDDEN_KEYWORD_ACCESS);
        }
    }

    @Transactional
    public void deleteKeyword(Long keywordId) {
        keywordRepository.deleteById(keywordId);
    }
}
