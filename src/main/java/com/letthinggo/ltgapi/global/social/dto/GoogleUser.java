package com.letthinggo.ltgapi.global.social.dto;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class GoogleUser extends OAuth2ProviderUser{

    public GoogleUser(Attributes attributes, OAuth2User oAuth2User, ClientRegistration clientRegistration){
        super(attributes.getMainAttributes(), oAuth2User, clientRegistration);
    }
    @Override
    public String getId() {
        return (String)getAttributes().get("sub");
    }

    @Override
    public String getUsername() {
        return new StringBuilder()
                .append(getProvider()).append("_")
                .append((String)getAttributes().get("sub")).toString();
    }
    @Override
    public String getNickname() {
        return (String)getAttributes().get("name");
    }

    @Override
    public Map<String, String> getAllowedServiceTerms(String accessToken) {
        return null;
    }


}
