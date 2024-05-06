package com.letthinggo.ltgapi.data.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestTestDto {
    @NotBlank
    String nickname;
    @NotBlank
    String email;
}
