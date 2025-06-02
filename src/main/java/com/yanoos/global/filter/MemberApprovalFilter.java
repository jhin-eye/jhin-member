package com.yanoos.global.filter;

import com.yanoos.global.entity.member.Member;
import com.yanoos.global.jwt.CustomPrincipal;
import com.yanoos.member.service.entity_service.member.MemberEntityService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberApprovalFilter extends BaseSkipPathFilter {

    private final MemberEntityService memberEntityService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (shouldSkip(request)) {
            log.info("회원 승인 필터 스킵: 요청 경로 = {}", request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof CustomPrincipal principal) {
            Long memberId = principal.getMemberId();

            Member member = memberEntityService.getMemberByMemberId(memberId);

            if (!member.isApproved()) {
                log.warn("회원 승인되지 않음: memberId = {}", memberId);
                response.sendRedirect("/?error=notApproved");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
