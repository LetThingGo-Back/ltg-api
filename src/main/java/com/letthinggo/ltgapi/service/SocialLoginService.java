package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.TokenResponseDto;

public interface SocialLoginService {
    public TokenResponseDto login(String providerName, String externalToken);
    public TokenResponseDto reissue(String refreshToken) throws Exception;

}
