package com.letthinggo.ltgapi.service.impl;

import com.letthinggo.ltgapi.data.dto.UserResponseDto;
import com.letthinggo.ltgapi.data.dto.UserRequestDto;
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

    public List<UserResponseDto> findUserTest(UserRequestDto userRequestDto){
        return userRepository.search(userRequestDto);
    }
}
