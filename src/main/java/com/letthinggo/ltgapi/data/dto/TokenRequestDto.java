package com.letthinggo.ltgapi.data.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenRequestDto {
    @NotBlank
    private String externalToken;
}
