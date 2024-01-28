package com.yanoos.global.jwt.service;

import com.yanoos.global.exception.BusinessException;
import com.yanoos.global.exception.code.CommonErrorCode;
import com.yanoos.global.jwt.TokenType;
import com.yanoos.global.jwt.dto.AccessTokenResponseDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Slf4j
@Service
public class JwtTokenService {
    @Value("${jwt.access-token-expiration-time}")
    private long ACCESS_TOKEN_EXPIRATION_TIME;
    @Value("${jwt.refresh-token-expiration-time}")
    private long REFRESH_TOKEN_EXPIRATION_TIME ;
    @Value("${jwt.secret-key}")
    private String JWT_SECRET_KEY;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    //=========================토큰 생성 관련 시작=========================
    public String generateAccessToken(Long memberId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION_TIME);
        SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(TokenType.ACCESS.name())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("memberId", memberId)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
    public String generateRefreshToken(Long memberId) {
        Date now = new Date();
        Date expiryDate = new Date(new Date().getTime() + REFRESH_TOKEN_EXPIRATION_TIME);
        SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(TokenType.REFRESH.name())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("memberId", memberId)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
    //=========================토큰 생성 관련 종료=========================


    //=========================토큰 검증 관련 시작=========================
    public boolean validateToken(String token,TokenType tokenType) {
        log.info("validate in");
        try {
            log.info("get claims start");
            Jws<Claims> claims = getClaims(token);
            log.info("check token type start");
            if(!claims.getBody().getSubject().equals(tokenType.name())){
                log.info("잘못된 토큰타입 입니다");
                throw new BusinessException(CommonErrorCode.INVALID_TOKEN_TYPE);
            }
            return !isTokenExpired(claims.getBody());
        }catch (ExpiredJwtException e){
            log.info("만료된 토큰입니다");
            throw new BusinessException(CommonErrorCode.TOKEN_EXPIRED);
            // return false;

        }catch (Exception e) {
            log.info("jwt 검증중 에러");
            // 여기서는 모든 예외를 잡아 false를 반환합니다.
            // 실제 사용시에는 예외 종류에 따라 다른 처리가 필요할 수 있습니다.
            e.printStackTrace();
            throw e;
            // return false;
        }
    }

    public String getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if(bearerToken !=null && bearerToken.startsWith(BEARER_PREFIX)){
            return bearerToken.substring(7);
        }
        throw new BusinessException(CommonErrorCode.NOT_VALID_TOKEN);
    }
    // 토큰의 만료 시간 확인
    private boolean isTokenExpired(Claims claims) {
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    public Long getUserIdFromJwt(String token) {
        Claims claims = getClaims(token).getBody();

        // 여기서 "memberId"는 토큰 생성 시에 정의한 커스텀 클레임의 이름입니다.
        // 토큰에 저장된 memberId를 Long 타입으로 반환합니다.
        return Long.parseLong(claims.get("memberId").toString());
    }
    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public Jws<Claims> getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token);
    }


    public ResponseEntity<AccessTokenResponseDto> regenerateAccessTokenByRefreshToken(HttpServletRequest request) {
        String jwt = getJwtFromRequest(request);
        validateToken(jwt,TokenType.REFRESH);
        Long memberId = getUserIdFromJwt(jwt);
        String token = generateAccessToken(memberId);
        return ResponseEntity.ok(AccessTokenResponseDto.builder().accessToken(token).build());

    }
}
