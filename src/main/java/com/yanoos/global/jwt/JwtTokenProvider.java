package com.yanoos.global.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Slf4j
@Service
public class JwtTokenProvider {
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 60*15*1000; // 15분
    @Value("${jwt.secret-key}")
    private String JWT_SECRET_KEY;

    public String generateAccessToken(Long memberId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION_TIME);
        SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(String.valueOf(memberId))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("memberId", memberId)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

}