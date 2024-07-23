package com.yanoos.member.controller.keyword;

import com.yanoos.global.exception.BusinessException;
import com.yanoos.global.exception.code.MemberErrorCode;
import com.yanoos.global.util.AuthUtil;
import com.yanoos.member.business_service.keyword.KeywordBusinessService;
import com.yanoos.member.controller.dto.PostKeywordIn;
import com.yanoos.member.controller.dto.PostKeywordOut;
import com.yanoos.member.entity.MapMemberKeyword;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/api/view/keyword")
public class KeywordViewController {
    private final AuthUtil authUtil;
    private final KeywordBusinessService keywordBusinessService;


    @GetMapping
    public String getKeywords(Model model){
        List<String> mapMemberKeywords=keywordBusinessService.getKeywordsByMemberId(authUtil.getMemberId());
        model.addAttribute("keywords",mapMemberKeywords);
        return "keyword";
    }


    @PostMapping()
    public String postKeyword(@RequestBody PostKeywordIn postKeywordIn) {
        validatePostKeywordIn(postKeywordIn);
        PostKeywordOut postKeywordOut = keywordBusinessService.postKeyword(postKeywordIn);
        return "redirect:/api/view/keyword";
    }

    private void validatePostKeywordIn(PostKeywordIn postKeywordIn) {
        Long memberId = authUtil.getMemberId();
        if(!memberId.equals(postKeywordIn.getMemberId())){
            throw new BusinessException(MemberErrorCode.MEMBER_NOT_FOUND);
        }
        log.info("validatePostKeywordIn success!");
    }


}
