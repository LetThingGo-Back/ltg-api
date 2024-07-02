package com.letthinggo.ltgapi.domain.sample.service;

import com.letthinggo.ltgapi.domain.sample.data.dto.UserResponseTestDto;

import java.util.List;

public interface UserTestService {
    public UserResponseTestDto findUserTest(Long id) throws Exception;

    public List<UserResponseTestDto> findAllTest() throws Exception;

}
