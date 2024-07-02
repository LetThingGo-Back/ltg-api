package com.letthinggo.ltgapi.domain.user.data.dto;

import com.letthinggo.ltgapi.domain.auth.data.entity.SocialLoginCode;
import com.letthinggo.ltgapi.global.social.dto.Authority;
import com.letthinggo.ltgapi.global.social.dto.ProviderUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;

@Data
public class UserDto {

    private Long userId;
    private SocialLoginCode socialCode;
    private String externalId;
    private String email;
    private String nickname;
    private String role;
    private String username;
    private List<? extends GrantedAuthority> authorities;

    Map<String, String> allowedServiceTerms; //동의약관리스트
    public UserDto(){

    }
    public UserDto(ProviderUser providerUser, String accessToken) {
        this.allowedServiceTerms = providerUser.getAllowedServiceTerms(accessToken);
        this.socialCode = SocialLoginCode.valueOf(providerUser.getProvider());
        this.externalId = providerUser.getId();
        this.email = providerUser.getEmail();
        this.authorities = providerUser.getAuthorities();
        this.role = Authority.ROLE_USER.getAuthority(); // TODO: 추후에 하드코딩 변경
        this.username = providerUser.getUsername();
    }
}
