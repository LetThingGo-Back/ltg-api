package com.letthinggo.ltgapi.social.dto;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class NaverUser extends OAuth2ProviderUser{

    public NaverUser(Attributes attributes, OAuth2User oAuth2User, ClientRegistration clientRegistration){
        super(attributes.getSubAttributes(), oAuth2User, clientRegistration);
    }

    @Override
    public String getId() {
        return (String)getAttributes().get("id");
    }

    @Override
    public String getUsername() {
        return new StringBuilder()
                .append(getProvider()).append("_")
                .append((String)getAttributes().get("id")).toString();
    }
    @Override
    public String getNickname() {
        return (String)getAttributes().get("nickname");
    }
}
