package com.letthinggo.ltgapi.service.impl;

import com.letthinggo.ltgapi.data.dto.RefreshTokenDto;
import com.letthinggo.ltgapi.data.entity.RefreshToken;
import com.letthinggo.ltgapi.data.entity.SocialLogin;
import com.letthinggo.ltgapi.data.repository.RefreshTokenRepository;
import com.letthinggo.ltgapi.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    @Override
    public Long createRefreshToken(RefreshTokenDto refreshTokenRequest) {
        RefreshToken refreshToken = RefreshToken.createRefreshToken(refreshTokenRequest);
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getUserId();
    }
}
