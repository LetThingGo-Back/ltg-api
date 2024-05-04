package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.UserCreateRequest;
import com.letthinggo.ltgapi.data.dto.UserResponseTestDto;
import com.letthinggo.ltgapi.data.dto.UserRequestTestDto;

import java.util.List;

public interface UserService {
    Long createUser(UserCreateRequest userDto);
    public List<UserResponseTestDto> findUserTest(UserRequestTestDto userRequestDto);

}
