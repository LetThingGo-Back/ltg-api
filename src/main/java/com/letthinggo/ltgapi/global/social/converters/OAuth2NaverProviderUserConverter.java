package com.letthinggo.ltgapi.global.social.converters;

import com.letthinggo.ltgapi.domain.auth.data.entity.SocialLoginCode;
import com.letthinggo.ltgapi.global.social.dto.NaverUser;
import com.letthinggo.ltgapi.global.social.dto.ProviderUser;
import com.letthinggo.ltgapi.global.social.util.OAuth2Util;

public final class OAuth2NaverProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(SocialLoginCode.NAVER.getSocialName())) {
            return null;
        }

        return new NaverUser(OAuth2Util.getSubAttributes(
                providerUserRequest.oAuth2User(), "response"),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration());
    }
}