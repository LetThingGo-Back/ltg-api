package com.letthinggo.ltgapi.domain.auth.service.impl;

import com.letthinggo.ltgapi.domain.auth.data.dto.RefreshTokenDto;
import com.letthinggo.ltgapi.domain.auth.data.dto.TokenResponseDto;
import com.letthinggo.ltgapi.domain.user.data.dto.UserDto;
import com.letthinggo.ltgapi.domain.auth.data.entity.RefreshToken;
import com.letthinggo.ltgapi.domain.auth.data.repository.RefreshTokenRepository;
import com.letthinggo.ltgapi.domain.auth.data.repository.AuthRepository;
import com.letthinggo.ltgapi.domain.auth.service.AuthService;
import com.letthinggo.ltgapi.global.exception.CommonException;
import com.letthinggo.ltgapi.global.exception.ErrorCode;
import com.letthinggo.ltgapi.global.jwt.JwtUtil;
import com.letthinggo.ltgapi.domain.auth.service.RefreshTokenService;
import com.letthinggo.ltgapi.global.social.dto.Authority;
import com.letthinggo.ltgapi.global.social.dto.CustomOAuth2User;
import com.letthinggo.ltgapi.global.social.service.CustomOAuth2UserService;

import io.micrometer.common.util.StringUtils;
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
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final InMemoryClientRegistrationRepository inMemoryRepository;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    @Override
    public TokenResponseDto login(String providerName, String externalToken) throws Exception {
        ClientRegistration clientRegistration = inMemoryRepository.findByRegistrationId(providerName);
        OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, externalToken, Instant.now(), Instant.now().plus(Duration.ofMinutes(1)));
        OAuth2UserRequest userRequest = new OAuth2UserRequest(clientRegistration, oAuth2AccessToken);

        CustomOAuth2User oAuth2User = (CustomOAuth2User)customOAuth2UserService.loadUser(userRequest);
        return createJwtToken(oAuth2User);
    }

    @Override
    public TokenResponseDto reissue(String refreshToken) throws Exception {
        // refreshToken이 null인 경우
        if(StringUtils.isBlank(refreshToken)) {
            throw new CommonException(ErrorCode.REFRESH_TOKEN_NULL);
        }
        // 유효기간 확인
        if(jwtUtil.isExpired(refreshToken)) {
            throw new CommonException(ErrorCode.REFRESH_TOKEN_EXPIRED);
        }

        // 토큰이 refresh인지 확인
        String category = jwtUtil.getCatgory(refreshToken);

        if(!"refresh".equals(category)) {
            throw new CommonException(ErrorCode.INVAILD_REFRESH_TOKEN);
        }

        Long userId = jwtUtil.getUserId(refreshToken);
        String role = jwtUtil.getRole(refreshToken);

        Optional<RefreshToken> oldRefreshToken= refreshTokenRepository.findByUserId(userId);
        if(!oldRefreshToken.isPresent()){
            throw new CommonException(ErrorCode.INVAILD_REFRESH_TOKEN);
        }
        if(!refreshToken.equals(oldRefreshToken.get().getRefreshToken())){
            throw new CommonException(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
        }
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setRole(role);

        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDto);

        return createJwtToken(customOAuth2User);
    }

    @Override
    public TokenResponseDto createJwtToken(CustomOAuth2User oAuth2User) throws Exception {
        String role = Authority.ROLE_USER.getAuthority();
        String accessToken  = jwtUtil.createJwt("access", oAuth2User.getUserId(), role, 86400000L); // 24 hours
        String refreshToken = jwtUtil.createJwt("refresh", oAuth2User.getUserId(), role, 2592000000L); //30 days

        // TODO: 추후에 redis로 변경
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
        refreshTokenDto.setUserId(oAuth2User.getUserId());
        refreshTokenDto.setRefreshToken(refreshToken);
        refreshTokenDto.setExpirationDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis() + 2592000000L), ZoneId.systemDefault()));
        refreshTokenService.createRefreshToken(refreshTokenDto);
        return TokenResponseDto.createToken(accessToken, refreshToken);
    }

}
