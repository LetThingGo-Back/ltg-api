package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.RefreshTokenDto;

public interface RefreshTokenService {
    Long createRefreshToken(RefreshTokenDto refreshTokenDto);
}
