package com.letthinggo.ltgapi.domain.sample.data.dto;

import com.letthinggo.ltgapi.domain.user.data.entity.Users;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

@Data
public class UserResponseTestDto {
    private Long id;
    private String nickname;
    private String email;

    @Builder
    @QueryProjection
    public UserResponseTestDto(Long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }

    public static UserResponseTestDto createInstance(Users user){
        return UserResponseTestDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }
}
