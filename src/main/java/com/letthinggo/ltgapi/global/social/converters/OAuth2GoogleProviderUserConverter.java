package com.letthinggo.ltgapi.global.social.converters;

import com.letthinggo.ltgapi.domain.auth.data.entity.SocialLoginCode;
import com.letthinggo.ltgapi.global.social.dto.GoogleUser;
import com.letthinggo.ltgapi.global.social.dto.ProviderUser;
import com.letthinggo.ltgapi.global.social.util.OAuth2Util;

public final class OAuth2GoogleProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(SocialLoginCode.GOOGLE.getSocialName())) {
            return null;
        }

        return new GoogleUser(OAuth2Util.getMainAttributes(
                providerUserRequest.oAuth2User()),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration());
    }
}