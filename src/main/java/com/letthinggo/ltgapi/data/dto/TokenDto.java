package com.letthinggo.ltgapi.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class TokenDto {
    private String accessToken;
    private String refreshToken;
    @Builder
    public TokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static TokenDto createToken(String accessToken, String refreshToken){
        return TokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }
}
