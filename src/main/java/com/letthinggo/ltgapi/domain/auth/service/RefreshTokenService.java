package com.letthinggo.ltgapi.domain.auth.service;

import com.letthinggo.ltgapi.domain.auth.data.dto.RefreshTokenDto;

public interface RefreshTokenService {
    public Long createRefreshToken(RefreshTokenDto refreshTokenDto);
}
