package com.yanoos.global.filter;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Set;

public abstract class BaseSkipPathFilter extends OncePerRequestFilter {

    private static final Set<String> EXCLUDED_PATHS = Set.of(
            "/", "/index", "/home", "/error"
    );

    private static final Set<String> EXCLUDED_PATH_PREFIXES = Set.of(
            "/api/kakao", "/api/token/by/refresh", "/css", "/js", "/img", "/static", "/favicon.ico"
    );

    protected boolean shouldSkip(HttpServletRequest request) {
        String uri = request.getRequestURI();

        // 정확히 일치하는 경로만 제외
        if (EXCLUDED_PATHS.contains(uri)) {
            return true;
        }

        // 접두어로만 판단할 URI는 /api 계열만 포함해야 함
        return EXCLUDED_PATH_PREFIXES.stream().anyMatch(uri::startsWith);
    }

}
