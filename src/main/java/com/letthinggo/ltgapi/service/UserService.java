package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.UserDto;
import com.letthinggo.ltgapi.data.dto.UserResponse;
import com.letthinggo.ltgapi.data.dto.UserResponseTestDto;

import java.util.List;

public interface UserService {
    public UserDto createUser(UserDto userDto);
    public UserResponseTestDto findUserTest(Long id) throws Exception;

    public List<UserResponseTestDto> findAllTest();

    public UserResponse findOne(Long id) throws Exception;
}
