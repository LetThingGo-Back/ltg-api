package com.letthinggo.ltgapi.data.dto.social;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;

public interface ProviderUser {
    public String getId();
    public String getUsername();
    public String getPassword();
    public String getEmail();

    public String getNickname();
    public String getProvider();

    public List<? extends GrantedAuthority> getAuthorities();
    public Map<String, Object> getAttributes();
}
