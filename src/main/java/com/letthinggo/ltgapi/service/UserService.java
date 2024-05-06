package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.UserDto;
import com.letthinggo.ltgapi.data.dto.UserResponseTestDto;
import com.letthinggo.ltgapi.data.dto.UserRequestTestDto;

import java.util.List;

public interface UserService {
    Long createUser(UserDto userDto);
    public List<UserResponseTestDto> findUserTest(UserRequestTestDto userRequestDto);

}
