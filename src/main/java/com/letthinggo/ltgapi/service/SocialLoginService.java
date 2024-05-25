package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.TokenResponseDto;
import com.letthinggo.ltgapi.social.dto.CustomOAuth2User;

public interface SocialLoginService {
    public TokenResponseDto login(String providerName, String externalToken) throws Exception;
    public TokenResponseDto reissue(String refreshToken) throws Exception;
    public TokenResponseDto createJwtToken(CustomOAuth2User oAuth2User) throws Exception;
}
