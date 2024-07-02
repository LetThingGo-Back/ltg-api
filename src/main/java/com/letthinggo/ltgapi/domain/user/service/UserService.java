package com.letthinggo.ltgapi.domain.user.service;

import com.letthinggo.ltgapi.domain.user.data.dto.UserDto;
import com.letthinggo.ltgapi.domain.user.data.dto.UserResponseDto;
import com.letthinggo.ltgapi.domain.sample.data.dto.UserResponseTestDto;

import java.util.List;

public interface UserService {
    public UserDto createUser(UserDto userDto);

    public UserResponseDto findOne(Long id) throws Exception;
}
