package com.yanoos.global.filter;

import com.yanoos.global.entity.member.Member;
import com.yanoos.global.jwt.CustomPrincipal;
import com.yanoos.global.jwt.TokenType;
import com.yanoos.global.jwt.service.JwtTokenService;
import com.yanoos.member.service.entity_service.member.MemberEntityService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends BaseSkipPathFilter {

    private final JwtTokenService jwtTokenService;
    private final MemberEntityService memberEntityService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (shouldSkip(request)) {
            log.info("JWT 인증 필터 스킵: 요청 경로 = {}", request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String jwt = jwtTokenService.getJwtFromRequest(request, TokenType.ACCESS);
            jwtTokenService.validateToken(jwt, TokenType.ACCESS, response);
            Long memberId = jwtTokenService.getUserIdFromJwt(jwt);

            Member member = memberEntityService.getMemberByMemberId(memberId);

            CustomPrincipal principal = new CustomPrincipal();
            principal.setMemberId(memberId);
            principal.setRole(member.getRole()); // 사용자 역할 설정
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(principal, null,
                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+member.getRole().toUpperCase())));

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("인증 필터 통과: memberId = {}", memberId);
        } catch (Exception e) {
            log.error("JWT 인증 실패: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
