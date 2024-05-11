package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.UserDto;
import com.letthinggo.ltgapi.data.dto.UserResponseTestDto;
import com.letthinggo.ltgapi.data.dto.UserRequestTestDto;
import com.letthinggo.ltgapi.response.ApiResponse;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    public UserResponseTestDto findUserTest(Long id) throws Exception;

    List<UserResponseTestDto> findAllTest();
}
