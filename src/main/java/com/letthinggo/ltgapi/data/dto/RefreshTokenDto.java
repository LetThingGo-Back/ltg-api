package com.letthinggo.ltgapi.data.dto;

import com.letthinggo.ltgapi.data.entity.SocialLoginCode;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RefreshTokenDto {
    private Long userId;
    private String refreshToken;
    private LocalDateTime expirationDate;
}
