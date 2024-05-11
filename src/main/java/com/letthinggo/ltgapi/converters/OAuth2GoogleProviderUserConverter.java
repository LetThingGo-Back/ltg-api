package com.letthinggo.ltgapi.converters;

import com.letthinggo.ltgapi.data.entity.SocialLoginCode;
import com.letthinggo.ltgapi.social.dto.GoogleUser;
import com.letthinggo.ltgapi.social.dto.ProviderUser;
import com.letthinggo.ltgapi.util.OAuth2Util;

public final class OAuth2GoogleProviderUserConverter implements ProviderUserConverter<ProviderUserRequest,ProviderUser> {
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