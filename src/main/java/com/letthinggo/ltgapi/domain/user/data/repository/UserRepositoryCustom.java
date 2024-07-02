package com.letthinggo.ltgapi.domain.user.data.repository;

import com.letthinggo.ltgapi.domain.sample.data.dto.UserResponseTestDto;
import com.letthinggo.ltgapi.domain.sample.data.dto.UserRequestTestDto;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserResponseTestDto> search(UserRequestTestDto userRequestDto);
    List<UserResponseTestDto> searchAll();
}
