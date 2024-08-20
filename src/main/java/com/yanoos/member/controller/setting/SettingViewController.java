package com.yanoos.member.controller.setting;

import com.yanoos.global.exception.BusinessException;
import com.yanoos.global.exception.code.MemberErrorCode;
import com.yanoos.global.util.AuthUtil;
import com.yanoos.member.business_service.keyword.KeywordBusinessService;
import com.yanoos.member.business_service.member.MemberBusinessService;
import com.yanoos.member.controller.dto.AsideMenu;
import com.yanoos.member.controller.member.GenerateTelegramUuidOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/view/settings")
public class SettingViewController {
    private final AuthUtil authUtil;
    private final KeywordBusinessService keywordBusinessService;
    private final MemberBusinessService memberBusinessService;

    @GetMapping("/telegram")
    public String getTelegramSettings(Model model){
        model.addAttribute("asideMenus",getAsideMenus());
        model.addAttribute("title","텔레그램 설정");
        return "setting/telegramSetting";
    }
    @PostMapping("/telegram")
    public String generateTelegramUuid(Model model){

        GenerateTelegramUuidOut generateTelegramUuidOut = memberBusinessService.generateTelegramUuid(authUtil.getMemberId());
        model.addAttribute("generateTelegramUuidOut", generateTelegramUuidOut);
        model.addAttribute("asideMenus",getAsideMenus());
        return "setting/telegramSettingUUidGenerated";
    }


    @GetMapping("/keyword")
    public String getKeywordSettings(Model model, RedirectAttributes redirectAttributes){
        List<String> mapMemberKeywords = new ArrayList<>();
        try {
            mapMemberKeywords=keywordBusinessService.getKeywordsByMemberId(authUtil.getMemberId());
        }catch (BusinessException e){
            if(e.getErrorCode().equals(MemberErrorCode.TELEGRAM_UUID_NOT_FOUND)){
                redirectAttributes.addFlashAttribute("alert","먼저 텔레그램 연동을 진행하세요");
                return "redirect:/api/view/settings/telegram";
            }else{
                throw e;
            }
        }
        model.addAttribute("asideMenus",getAsideMenus());
        model.addAttribute("keywords",mapMemberKeywords);
        model.addAttribute("title","키워드 설정");
        return "setting/keywordSetting";
    }

    private List<AsideMenu> getAsideMenus(){
        return new ArrayList<>(List.of(new AsideMenu("keywords","/api/view/settings/keyword"),
                new AsideMenu("telegram","/api/view/settings/telegram")));
    }
}
