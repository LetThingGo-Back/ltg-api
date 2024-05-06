package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.RefreshTokenDto;

public interface SocialLoginService {
    int updateRefreshToken(RefreshTokenDto refreshTokenDto);
}
