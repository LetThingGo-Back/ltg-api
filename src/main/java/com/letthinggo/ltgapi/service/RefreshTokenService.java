package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.RefreshTokenDto;

public interface RefreshTokenService {
    public Long createRefreshToken(RefreshTokenDto refreshTokenDto);
}
