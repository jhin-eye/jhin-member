package com.yanoos.global.interceptor;

import com.yanoos.global.entity.member.Member;
import com.yanoos.global.jwt.TokenType;
import com.yanoos.global.jwt.service.JwtTokenService;
import com.yanoos.member.service.entity_service.member.MemberEntityService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final MemberEntityService memberEntityService;
    private final JwtTokenService jwtTokenService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("start authorization interceptor");
        String jwt = jwtTokenService.getJwtFromRequest(request, TokenType.ACCESS);
        //jwt validate는 인증 인터셉터에서 이미 수행되었음

        Long memberId = jwtTokenService.getUserIdFromJwt(jwt);

        Member member = memberEntityService.getMemberByMemberId(memberId);

        boolean isApproved = member.isApproved();
        log.info("isApproved = {}",isApproved);
        if(!isApproved){
            response.sendRedirect("/?error=notApproved");
            return false;
        }
        return true;
    }
}
