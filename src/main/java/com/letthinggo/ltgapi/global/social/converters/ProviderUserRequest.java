package com.letthinggo.ltgapi.global.social.converters;

import com.letthinggo.ltgapi.domain.user.data.entity.Users;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

public record ProviderUserRequest (ClientRegistration clientRegistration, OAuth2User oAuth2User, Users user){
    public ProviderUserRequest(ClientRegistration clientRegistration, OAuth2User oAuth2User){
        this(clientRegistration,oAuth2User,null);
    };

    public ProviderUserRequest(Users user){
        this(null,null,user);
    };
}
