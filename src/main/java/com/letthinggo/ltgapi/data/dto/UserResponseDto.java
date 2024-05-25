package com.letthinggo.ltgapi.data.dto;

import com.letthinggo.ltgapi.data.entity.Users;
import lombok.Builder;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long userId;
    private String nickname;
    private String email;
    @Builder
    public UserResponseDto(Users user) {
        this.userId = user.getId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
    }
}
