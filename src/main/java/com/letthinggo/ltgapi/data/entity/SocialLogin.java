package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.entity.common.CreateDateTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialLogin {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;
    @Convert(converter = SocialLoginCodeConverter.class)
    private SocialLoginCode socialCode;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expirationDate;
    @Embedded
    private CreateDateTime createDateTime;

}
