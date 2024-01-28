package com.yanoos.global.jwt.controller;

import com.yanoos.global.jwt.dto.AccessTokenResponseDto;
import com.yanoos.global.jwt.service.JwtTokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class TokenController {
    private final JwtTokenService jwtTokenService;
    @GetMapping("/by/refresh")
    public ResponseEntity<AccessTokenResponseDto> createAccessTokenByRefreshToken(HttpServletRequest request){
        log.info("createAccessTokenByRefreshToken in");
        return jwtTokenService.regenerateAccessTokenByRefreshToken(request);

    }
}
