package com.letthinggo.ltgapi.data.dto;

import com.letthinggo.ltgapi.data.entity.Users;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

@Data
public class UserResponse {
    private Long userId;
    private String nickname;
    private String email;
    @Builder
    public UserResponse(Users user) {
        this.userId = user.getId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
    }
}
