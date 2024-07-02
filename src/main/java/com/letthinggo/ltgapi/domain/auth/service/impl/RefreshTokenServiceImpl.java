package com.letthinggo.ltgapi.domain.auth.service.impl;

import com.letthinggo.ltgapi.domain.auth.data.dto.RefreshTokenDto;
import com.letthinggo.ltgapi.domain.auth.data.entity.RefreshToken;
import com.letthinggo.ltgapi.domain.auth.data.repository.RefreshTokenRepository;
import com.letthinggo.ltgapi.domain.auth.service.RefreshTokenService;
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
    public Long createRefreshToken(RefreshTokenDto refreshTokenDto) {
        RefreshToken refreshToken = RefreshToken.createRefreshToken(refreshTokenDto);
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getUserId();
    }
}
