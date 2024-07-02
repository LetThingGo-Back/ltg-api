package com.letthinggo.ltgapi.global.social.dto;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
