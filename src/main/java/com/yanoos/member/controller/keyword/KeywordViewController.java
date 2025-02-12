package com.yanoos.member.controller.keyword;

import com.yanoos.global.entity.member.Member;
import com.yanoos.global.exception.BusinessException;
import com.yanoos.global.exception.code.MemberErrorCode;
import com.yanoos.global.util.AuthUtil;
import com.yanoos.member.service.business_service.keyword.KeywordBusinessService;
import com.yanoos.member.service.business_service.member.MemberBusinessService;
import com.yanoos.member.controller.dto.PostKeywordIn;
import com.yanoos.member.controller.dto.PostKeywordOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/api/view/keyword")
public class KeywordViewController {
    private final AuthUtil authUtil;
    private final KeywordBusinessService keywordBusinessService;
    private final MemberBusinessService memberBusinessService;


    @GetMapping
    public String getKeywords(Model model){
        List<String> mapMemberKeywords = new ArrayList<>();
        Long memberId = authUtil.getMemberId();
        Member member = memberBusinessService.getMemberById(memberId);
        model.addAttribute("keywords",member.getKeywords());
        return "setting/keywordSetting";
    }




    @PostMapping()
    public String postKeyword(@ModelAttribute PostKeywordIn postKeywordIn) {
        validatePostKeywordIn(postKeywordIn);
        PostKeywordOut postKeywordOut = keywordBusinessService.postKeyword(postKeywordIn);
        return "redirect:/api/view/settings/keyword";
    }
    @DeleteMapping
    public String deleteKeyword(@RequestParam("keywordId") Long keywordId){
        Long memberId = authUtil.getMemberId();
        keywordBusinessService.deleteKeyword(memberId,keywordId);
        return "redirect:/api/view/settings/keyword";
    }




    private void validatePostKeywordIn(PostKeywordIn postKeywordIn) {
        log.info("validatePostKeywordIn success!");
    }


}
