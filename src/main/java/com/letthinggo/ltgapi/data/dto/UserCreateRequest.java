package com.letthinggo.ltgapi.data.dto;

import com.letthinggo.ltgapi.data.entity.SocialLoginCode;
import com.letthinggo.ltgapi.data.entity.Users;
import com.letthinggo.ltgapi.oauth2.ProviderUser;
import io.micrometer.common.util.StringUtils;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
public class UserCreateRequest {
    private SocialLoginCode socialCode;
    private String externalId;
    private String email;
    private String nickname;
    private List<? extends GrantedAuthority> authorities;

    public UserCreateRequest(ProviderUser providerUser) {
        this.socialCode = SocialLoginCode.valueOf(providerUser.getProvider());
        this.externalId = providerUser.getId();
        this.email = providerUser.getEmail();
        this.nickname = StringUtils.isBlank(providerUser.getNickName()) ? "test" : providerUser.getNickName();
        this.authorities = providerUser.getAuthorities();
    }
}
