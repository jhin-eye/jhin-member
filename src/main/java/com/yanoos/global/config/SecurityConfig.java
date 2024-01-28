// package com.yanoos.global.config;
//
// import com.yanoos.global.jwt.JwtTokenFilter;
// import lombok.RequiredArgsConstructor;
// import org.springframework.context.annotation.Bean;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
// import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
// import org.springframework.security.web.util.matcher.RequestMatcher;
//
// @RequiredArgsConstructor
// @EnableWebSecurity
// public class SecurityConfig {
//     private final JwtTokenFilter jwtTokenFilter;
//
//     @Bean//TODO 인증설정
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         // /token/** 경로에 대한 request matcher
//         RequestMatcher tokenPaths = new AntPathRequestMatcher("/token/**");
//         // /token/** 경로를 제외하는 negative request matcher
//         RequestMatcher notTokenPaths = new NegatedRequestMatcher(tokenPaths);
//         http
//         // CSRF 보호 설정 변경
//         .csrf(csrf -> csrf.disable())
//         // 보안 규칙 설정 변경
//         .authorizeRequests(authorizeRequests ->
//                 authorizeRequests
//                         .requestMatchers("/**").permitAll()
//         )
//         .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class).requestMatchers(notTokenPaths);
//
//         // 추가적인 설정...
//         ;
//         return http.build();
//     }
// }
