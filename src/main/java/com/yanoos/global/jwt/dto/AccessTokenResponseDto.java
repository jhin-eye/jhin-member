package com.yanoos.global.jwt.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AccessTokenResponseDto {
    private String accessToken;
}
