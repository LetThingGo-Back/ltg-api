package com.letthinggo.ltgapi.service.impl;

import com.letthinggo.ltgapi.data.dto.CustomOAuth2User;
import com.letthinggo.ltgapi.data.dto.RefreshTokenDto;
import com.letthinggo.ltgapi.data.dto.TokenResponseDto;
import com.letthinggo.ltgapi.data.repository.SocialLoginRepository;
import com.letthinggo.ltgapi.jwt.JwtUtil;
import com.letthinggo.ltgapi.service.RefreshTokenService;
import com.letthinggo.ltgapi.social.service.CustomOAuth2UserService;
import com.letthinggo.ltgapi.service.SocialLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SocialLoginServiceImpl implements SocialLoginService {
    private final SocialLoginRepository socialLoginRepository;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final InMemoryClientRegistrationRepository inMemoryRepository;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    @Transactional
    @Override
    public TokenResponseDto login(String providerName, String externalToken) {
        ClientRegistration clientRegistration = inMemoryRepository.findByRegistrationId(providerName);
        OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, externalToken, Instant.now(), Instant.now().plus(Duration.ofMinutes(1)));
        OAuth2UserRequest userRequest = new OAuth2UserRequest(clientRegistration, oAuth2AccessToken);

        CustomOAuth2User oAuth2User = (CustomOAuth2User)customOAuth2UserService.loadUser(userRequest);
        return createJwtToken(oAuth2User);
    }
    @Transactional
    public TokenResponseDto createJwtToken(CustomOAuth2User oAuth2User) {
        String role = "ROLE_USER";
        String accessToken  = jwtUtil.createJwt("access", oAuth2User.getUserId(), role, 86400000L); // 24 hours
        String refreshToken = jwtUtil.createJwt("refresh", oAuth2User.getUserId(), role, 2592000000L); //30 days

        // TODO: 추후에 redis로 변경
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
        refreshTokenDto.setUserId(oAuth2User.getUserId());
        refreshTokenDto.setSocialCode(oAuth2User.getSocialCode());
        refreshTokenDto.setExternalId(oAuth2User.getExternalId());
        refreshTokenDto.setRefreshToken(refreshToken);
        refreshTokenDto.setExpirationDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis() + 2592000000L), ZoneId.systemDefault()));
        refreshTokenService.createRefreshToken(refreshTokenDto);
        return TokenResponseDto.createToken(accessToken, refreshToken);
    }

}
