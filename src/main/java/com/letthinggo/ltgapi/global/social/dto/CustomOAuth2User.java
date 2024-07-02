package com.letthinggo.ltgapi.global.social.dto;

import com.letthinggo.ltgapi.domain.user.data.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
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
        return userDto.getUsername();
    }

    public Long getUserId() { return userDto.getUserId(); }

}