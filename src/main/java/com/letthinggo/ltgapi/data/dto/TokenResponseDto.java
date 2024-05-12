package com.letthinggo.ltgapi.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
    @Builder
    public TokenResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static TokenResponseDto createToken(String accessToken, String refreshToken){
        return TokenResponseDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }
}
