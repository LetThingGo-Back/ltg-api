package com.letthinggo.ltgapi.data.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long userId;
    private String nickname;
    private String email;

    @QueryProjection
    public UserResponseDto(Long id, String nickname, String email) {
        this.userId = id;
        this.nickname = nickname;
        this.email = email;
    }
}
