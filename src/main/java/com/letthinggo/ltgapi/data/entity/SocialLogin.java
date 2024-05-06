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
    @Column(name="REFRESH_TOKEN")
    private String refreshToken;

    @Column(name="EXPIRATION_DATE")
    private LocalDateTime expirationDate;
    @Builder
    public SocialLogin(Users user, SocialLoginCode socialCode, String externalId, String accessToken, String refreshToken, LocalDateTime expirationDate) {
        this.user = user;
        this.socialCode = socialCode;
        this.externalId = externalId;
        this.refreshToken = refreshToken;
        this.expirationDate = expirationDate;
    }

    public static SocialLogin createSocialLogin(UserDto userDto, Users user) {
        return SocialLogin.builder() .user(user)
                        .socialCode(userDto.getSocialCode())
                        .externalId(userDto.getExternalId())
                        .build();
    }

    public static SocialLogin updateRefreshToken(RefreshTokenDto refreshTokenDto) {
        return SocialLogin.builder()
                .socialCode(refreshTokenDto.getSocialCode())
                .externalId(refreshTokenDto.getExternalId())
                .refreshToken(refreshTokenDto.getRefreshToken())
                .expirationDate(refreshTokenDto.getExpirationDate())
                .build();
    }
}
