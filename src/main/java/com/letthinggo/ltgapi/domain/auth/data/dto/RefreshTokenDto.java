package com.letthinggo.ltgapi.domain.auth.data.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RefreshTokenDto {
    private Long userId;
    private String refreshToken;
    private LocalDateTime expirationDate;
}
