package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.dto.UserResponseDto;
import com.letthinggo.ltgapi.data.dto.UserRequestDto;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserResponseDto> search(UserRequestDto userRequestDto);
}
