package com.letthinggo.ltgapi.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestTestDto {

    @Schema(title="사용자 닉네임", description = "사용자의 닉네임을 입력합니다.", example = "test")
    @NotBlank
    String nickname;
    @Schema(title="사용자 이메일", description = "사용자의 이메일을 입력합니다.", example = "test@gmail.com")
    @NotBlank
    String email;
}
