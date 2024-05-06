package com.letthinggo.ltgapi.service.impl;

import com.letthinggo.ltgapi.data.dto.RefreshTokenDto;
import com.letthinggo.ltgapi.data.entity.SocialLogin;
import com.letthinggo.ltgapi.data.repository.SocialLoginRepository;
import com.letthinggo.ltgapi.service.SocialLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SocialLoginServiceImpl implements SocialLoginService {
    private final SocialLoginRepository socialLoginRepository;

    @Transactional
    @Override
    public int updateRefreshToken(RefreshTokenDto refreshTokenRequest) {
        SocialLogin socialLogin = SocialLogin.updateRefreshToken(refreshTokenRequest);
        return socialLoginRepository.updateRefreshTokenAndExpirationDate(socialLogin);
    }
}
