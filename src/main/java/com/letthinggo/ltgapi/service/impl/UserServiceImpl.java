package com.letthinggo.ltgapi.service.impl;

import com.letthinggo.ltgapi.data.dto.UserResponseTestDto;
import com.letthinggo.ltgapi.data.dto.UserRequestTestDto;
import com.letthinggo.ltgapi.data.repository.UserRepository;
import com.letthinggo.ltgapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public List<UserResponseTestDto> findUserTest(UserRequestTestDto userRequestDto){
        return userRepository.search(userRequestDto);
    }
}
