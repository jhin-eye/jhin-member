package com.yanoos.member.controller.setting;

import com.yanoos.global.entity.member.Keyword;
import com.yanoos.global.entity.member.Member;
import com.yanoos.global.exception.BusinessException;
import com.yanoos.global.exception.code.MemberErrorCode;
import com.yanoos.global.jwt.TokenType;
import com.yanoos.global.util.AuthUtil;
import com.yanoos.member.service.business_service.kakao.KakaoLoginService;
import com.yanoos.member.service.business_service.keyword.KeywordBusinessService;
import com.yanoos.member.service.business_service.member.MemberBusinessService;
import com.yanoos.member.controller.dto.AsideMenu;
import com.yanoos.member.controller.member.GenerateTelegramUuidOut;
import com.yanoos.member.service.entity_service.member.MemberEntityService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/view/settings")
public class SettingViewController {
    private final MemberEntityService memberEntityService;
    @Value("${kakao.client-id}")
    private String KAKAO_API_KEY;
    @Value("${kakao.logout-redirect-uri}")
    private String KAKAO_LOGOUT_REDIRECT_URI;
    @Value("${telegram.bot.username}")
    private String telegramBotUsername;


    private final AuthUtil authUtil;
    private final KeywordBusinessService keywordBusinessService;
    private final MemberBusinessService memberBusinessService;
    private final KakaoLoginService kakaoLoginService;


    @GetMapping("/logout/kakao")
    public void kakaoLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 쿠키 삭제
        Cookie accessTokenCookie = new Cookie(TokenType.ACCESS.name(), null);
        accessTokenCookie.setMaxAge(0);
        accessTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);

        Cookie refreshTokenCookie = new Cookie(TokenType.REFRESH.name(), null);
        refreshTokenCookie.setMaxAge(0);
        refreshTokenCookie.setPath("/");
        response.addCookie(refreshTokenCookie);

        // 홈으로 이동
        // 리다이렉트할 URL 생성
        String redirectUrl = "https://kauth.kakao.com/oauth/logout?client_id=" + KAKAO_API_KEY + "&logout_redirect_uri=" + KAKAO_LOGOUT_REDIRECT_URI;

        // 클라이언트에게 리다이렉트 응답을 보냄
        response.sendRedirect(redirectUrl);
    }

    @GetMapping("/telegram")
    public String getTelegramSettings(Model model) {
        model.addAttribute("asideMenus", getAsideMenus());
        model.addAttribute("title", "텔레그램 설정");
        return "setting/telegramSetting";
    }

    @PostMapping("/telegram")
    public String generateTelegramUuid(Model model) {

        GenerateTelegramUuidOut generateTelegramUuidOut = memberBusinessService.generateTelegramUuid(authUtil.getMemberId());
        model.addAttribute("generateTelegramUuidOut", generateTelegramUuidOut);
        model.addAttribute("asideMenus", getAsideMenus());
        model.addAttribute("telegramBotUsername", telegramBotUsername);
        return "setting/telegramSettingUUidGenerated";
    }


    @GetMapping("/keyword")
    public String getKeywordSettings(Model model, RedirectAttributes redirectAttributes) {
        Long memberId = authUtil.getMemberId();
        Member member = memberBusinessService.getMemberById(memberId);
        List<Keyword> keywords = member.getKeywords();


        if (member.getMapMemberTelegramUsers().isEmpty()){
            redirectAttributes.addFlashAttribute("alert", "먼저 텔레그램 연동을 진행하세요");
            return "redirect:/api/view/settings/telegram";
        }
        model.addAttribute("asideMenus", getAsideMenus());
        model.addAttribute("keywords", keywords);
        model.addAttribute("title", "키워드 설정");
        return "setting/keywordSetting";
    }
    private List<AsideMenu> getAsideMenus () {
        return new ArrayList<>(List.of(new AsideMenu("keywords", "/api/view/settings/keyword"),
                new AsideMenu("telegram", "/api/view/settings/telegram")));
    }
}
