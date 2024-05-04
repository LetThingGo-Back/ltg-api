package com.letthinggo.ltgapi.data.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class UserResponseTestDto {
    private Long id;
    private String nickname;
    private String email;

    @QueryProjection
    public UserResponseTestDto(Long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }
}
