package com.letthinggo.ltgapi.data.dto;

import com.letthinggo.ltgapi.data.entity.SocialLoginCode;
import com.letthinggo.ltgapi.social.dto.ProviderUser;
import io.micrometer.common.util.StringUtils;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

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

    public UserDto(){

    }
    public UserDto(ProviderUser providerUser) {
        this.socialCode = SocialLoginCode.valueOf(providerUser.getProvider());
        this.externalId = providerUser.getId();
        this.email = providerUser.getEmail();
        this.nickname = StringUtils.isBlank(providerUser.getNickname()) ? "test" : providerUser.getNickname(); // TODO: 닉네임 자동 생성으로 변경
        this.authorities = providerUser.getAuthorities();
        this.role = "ROLE_USER"; // TODO: 추후에 하드코딩 변경
        this.username = providerUser.getUsername();
    }
}
