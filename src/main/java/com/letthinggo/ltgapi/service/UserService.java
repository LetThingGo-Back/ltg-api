package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.UserResponseDto;
import com.letthinggo.ltgapi.data.dto.UserRequestDto;

import java.util.List;

public interface UserService {
    public List<UserResponseDto> findUserTest(UserRequestDto userRequestDto);
}
