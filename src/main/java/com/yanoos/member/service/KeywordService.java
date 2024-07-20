package com.yanoos.member.service;

import com.yanoos.member.service.dto.CreateKeywordIn;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional(readOnly = true)
public class KeywordService {
    @Transactional
    public void createKeyword(CreateKeywordIn createKeywordin){

    }
}
