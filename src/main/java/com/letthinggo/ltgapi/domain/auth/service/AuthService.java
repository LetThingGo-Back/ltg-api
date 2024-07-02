package com.letthinggo.ltgapi.domain.auth.service;

import com.letthinggo.ltgapi.domain.auth.data.dto.TokenResponseDto;
import com.letthinggo.ltgapi.global.social.dto.CustomOAuth2User;

public interface AuthService {
    public TokenResponseDto login(String providerName, String externalToken) throws Exception;
    public TokenResponseDto reissue(String refreshToken) throws Exception;
    public TokenResponseDto createJwtToken(CustomOAuth2User oAuth2User) throws Exception;
}
