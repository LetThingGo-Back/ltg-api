package com.letthinggo.ltgapi.social.dto;

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

    public  Map<String, String> getAllowedServiceTerms(String accessToken);

    public List<? extends GrantedAuthority> getAuthorities();
    public Map<String, Object> getAttributes();
}
