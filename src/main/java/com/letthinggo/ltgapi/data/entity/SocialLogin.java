package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.dto.RefreshTokenDto;
import com.letthinggo.ltgapi.data.dto.UserDto;
import com.letthinggo.ltgapi.data.entity.common.BaseDateTime;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="SOCIAL_LOGIN")
public class SocialLogin extends BaseDateTime{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SOCIAL_LOGIN_ID")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private Users user;
    @Convert(converter = SocialLoginCodeConverter.class)
    @Column(name="SOCIAL_CODE")
    private SocialLoginCode socialCode;
    @Column(name="EXTERNAL_ID")
    private String externalId;
    @Builder
    public SocialLogin(Users user, SocialLoginCode socialCode, String externalId) {
        this.user = user;
        this.socialCode = socialCode;
        this.externalId = externalId;
    }

    public static SocialLogin createSocialLogin(UserDto userDto, Users user) {
        return SocialLogin.builder() .user(user)
                        .socialCode(userDto.getSocialCode())
                        .externalId(userDto.getExternalId())
                        .build();
    }

}
