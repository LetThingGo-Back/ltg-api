package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.TokenDto;

public interface SocialLoginService {
    TokenDto login(String providerName, String code);
}
