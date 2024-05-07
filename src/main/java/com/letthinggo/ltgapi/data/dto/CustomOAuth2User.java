package com.letthinggo.ltgapi.data.dto;

import com.letthinggo.ltgapi.data.entity.SocialLoginCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {
    private final UserDto userDto;

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userDto.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getName() {
        return userDto.getNickname();
    }

    public Long getUserId() { return userDto.getUserId(); }

    public String getUsername() {
        return userDto.getUsername();
    }

    public SocialLoginCode getSocialCode() {
        return userDto.getSocialCode();
    }

    public String getExternalId() {
        return userDto.getExternalId();
    }
}