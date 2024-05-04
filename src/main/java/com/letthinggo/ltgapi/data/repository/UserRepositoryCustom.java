package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.dto.UserResponseTestDto;
import com.letthinggo.ltgapi.data.dto.UserRequestTestDto;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserResponseTestDto> search(UserRequestTestDto userRequestDto);
}
