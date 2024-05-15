package com.letthinggo.ltgapi.data.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenRequestDto {
    @NotBlank(message = "외부 토큰은 필수 입력값입니다.")
    private String externalToken;
}
