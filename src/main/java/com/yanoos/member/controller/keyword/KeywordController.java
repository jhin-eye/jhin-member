package com.yanoos.member.controller.keyword;

import com.yanoos.global.exception.BusinessException;
import com.yanoos.global.exception.code.MemberErrorCode;
import com.yanoos.global.util.AuthUtil;
import com.yanoos.member.controller.dto.PostKeywordIn;
import com.yanoos.member.business_service.keyword.KeywordBusinessService;
import com.yanoos.member.controller.dto.PostKeywordOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/keyword")
public class KeywordController {
    private final AuthUtil authUtil;
    private final KeywordBusinessService keywordBusinessService;


    /**
     * 계정 키워드 조회
     *
     * @return
     */
    @GetMapping
    public List<String> getKeywords(){
        Long memberId = authUtil.getMemberId();
        List<String> mapMemberKeywords=keywordBusinessService.getKeywordsByMemberId(memberId);
        return mapMemberKeywords;
    }
    /**
     * 계정에 키워드 등록
     * @param postKeywordIn
     * @return
     */
    @PostMapping()
    public PostKeywordOut postKeyword(@RequestBody PostKeywordIn postKeywordIn) {
        validatePostKeywordIn(postKeywordIn);
        PostKeywordOut postKeywordOut = keywordBusinessService.postKeyword(postKeywordIn);
        return postKeywordOut;
    }

    private void validatePostKeywordIn(PostKeywordIn postKeywordIn) {
        Long memberId = authUtil.getMemberId();
        if(!memberId.equals(postKeywordIn.getMemberId())){
            throw new BusinessException(MemberErrorCode.MEMBER_NOT_FOUND);
        }
        log.info("validatePostKeywordIn success!");
    }
}
