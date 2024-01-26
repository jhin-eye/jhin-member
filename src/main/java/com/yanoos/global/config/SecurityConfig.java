package com.yanoos.global.config;

import com.yanoos.global.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;

    @Bean//TODO 인증설정
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        // CSRF 보호 설정 변경
        .csrf(csrf -> csrf.disable())
        // 보안 규칙 설정 변경
        .authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/**").permitAll()
        )
        .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 추가적인 설정...
        ;
        return http.build();
    }
}
