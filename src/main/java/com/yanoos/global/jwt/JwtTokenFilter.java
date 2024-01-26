package com.yanoos.global.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
@Component
@Slf4j
//OncePerRequestFilter가 스프링 빈인가? 그래서 생성자 주입이 되는건가? 그럼 RequiredArgsConstructor도 가능?
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenVerifier jwtTokenVerifier;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJwtFromRequest(request);
        log.info("jwt = {}",jwt);
        if(jwt != null && jwtTokenVerifier.validateToken(jwt)){//jwt 유효성 검증
            log.info("in");
            Long memberId = jwtTokenVerifier.getUserIdFromJwt(jwt);//jwt에서 사용자 id 추출
            CustomPrincipal principal = new CustomPrincipal();
            principal.setMemberId(memberId);
            UsernamePasswordAuthenticationToken authentication  =
                    new UsernamePasswordAuthenticationToken(principal, null,
                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));//TODO 역할 구현
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


            // 인증 세부 정보 설정

            SecurityContextHolder.getContext().setAuthentication(authentication);
            // SecurityContext에 인증 정보 저장
        }

        filterChain.doFilter(request, response); // 다음 필터로 요청과 응답 전달
    }

    private String getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if(bearerToken !=null && bearerToken.startsWith(BEARER_PREFIX)){
            return bearerToken.substring(7);
        }
        return null;
    }
}
