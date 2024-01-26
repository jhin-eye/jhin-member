package com.yanoos.global.jwt;

import com.yanoos.global.exception.BusinessException;
import com.yanoos.global.exception.code.CommonErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Slf4j
@Service
public class JwtTokenVerifier {
    @Value("${jwt.secret-key}")
    private String secretKey;


    public boolean validateToken(String token) {
        log.info("validate in");
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);

            return !isTokenExpired(claims.getBody());
        } catch (Exception e) {
            // 여기서는 모든 예외를 잡아 false를 반환합니다.
            // 실제 사용시에는 예외 종류에 따라 다른 처리가 필요할 수 있습니다.
            e.printStackTrace();
            return false;
        }
    }
    // 토큰의 만료 시간 확인
    private boolean isTokenExpired(Claims claims) {
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    public Long getUserIdFromJwt(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        // 여기서 "memberId"는 토큰 생성 시에 정의한 커스텀 클레임의 이름입니다.
        // 토큰에 저장된 memberId를 Long 타입으로 반환합니다.
        return Long.parseLong(claims.get("memberId").toString());
    }
    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }


}
