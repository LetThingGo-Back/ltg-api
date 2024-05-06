package com.letthinggo.ltgapi.data.dto;

import com.letthinggo.ltgapi.data.entity.SocialLoginCode;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RefreshTokenDto {
    private SocialLoginCode socialCode;
    private String externalId;
    private String refreshToken;
    private LocalDateTime expirationDate;
}
