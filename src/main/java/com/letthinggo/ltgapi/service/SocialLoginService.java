package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.TokenResponseDto;

public interface SocialLoginService {
    TokenResponseDto login(String providerName, String externalToken);
}
