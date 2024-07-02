package com.letthinggo.ltgapi.domain.auth.data.entity;

import com.letthinggo.ltgapi.domain.user.data.entity.Users;
import com.letthinggo.ltgapi.domain.auth.data.dto.RefreshTokenDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="REFRESH_TOKEN")
public class RefreshToken {
    @Id
    @Column(name="USER_ID")
    private Long userId;

    @MapsId(value = "userId")
    @OneToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private Users user;

    @Column(name="REFRESH_TOKEN")
    private String refreshToken;

    @Column(name="EXPIRATION_DATE")
    private LocalDateTime expirationDate;

    @Builder
    public RefreshToken(Long userId, Users user, String refreshToken, LocalDateTime expirationDate) {
        this.userId = userId;
        this.user = user;
        this.refreshToken = refreshToken;
        this.expirationDate = expirationDate;
    }

    public static RefreshToken createRefreshToken(RefreshTokenDto refreshTokenDto) {
        return RefreshToken.builder()
                .userId(refreshTokenDto.getUserId())
                .refreshToken(refreshTokenDto.getRefreshToken())
                .expirationDate(refreshTokenDto.getExpirationDate())
                .build();
    }
}
