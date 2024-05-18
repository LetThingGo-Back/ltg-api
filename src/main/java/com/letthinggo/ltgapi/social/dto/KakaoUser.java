package com.letthinggo.ltgapi.social.dto;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class KakaoUser extends OAuth2ProviderUser {
    private final Map<String,Object> subAttributes;
    private final Map<String,Object> otherAttributes;

    public KakaoUser(Attributes attributes, OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        super(attributes.getMainAttributes(), oAuth2User, clientRegistration);
        this.subAttributes = attributes.getSubAttributes();
        this.otherAttributes = attributes.getOtherAttributes();

    }

    @Override
    public String getId() {
        return Long.toString((Long)getAttributes().get("id"));
    }

    @Override
    public String getUsername() {
        return new StringBuilder()
                .append(getProvider()).append("_")
                .append(Long.toString((Long)getAttributes().get("id"))).toString();
    }

    @Override
    public String getNickname() {
        return (String)otherAttributes.get("nickname");
    }

    @Override
    public Map<String, String> getAllowedServiceTerms(String accessToken) {
        return null;
    }

    @Override
    public String getEmail() {
        return(String)subAttributes.get("email");
    }

}