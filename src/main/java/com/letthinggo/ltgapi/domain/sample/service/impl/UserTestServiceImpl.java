package com.letthinggo.ltgapi.domain.sample.service.impl;

import com.letthinggo.ltgapi.domain.sample.data.dto.UserRequestTestDto;
import com.letthinggo.ltgapi.domain.sample.data.dto.UserResponseTestDto;
import com.letthinggo.ltgapi.domain.sample.data.repository.UserTestRepository;
import com.letthinggo.ltgapi.domain.sample.service.UserTestService;
import com.letthinggo.ltgapi.domain.user.data.entity.Users;
import com.letthinggo.ltgapi.global.exception.ErrorCode;
import com.letthinggo.ltgapi.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserTestServiceImpl implements UserTestService {
    private final UserTestRepository userRepository;

    /**
     * 테스트용 사용 안함
     * @param userRequestDto
     * @return
     */
    public List<UserResponseTestDto> findUserTest(UserRequestTestDto userRequestDto){
        return userRepository.search(userRequestDto);
    }

    @Override
    public UserResponseTestDto findUserTest(Long id) throws Exception {
        Optional<Users> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND);
        }
        return UserResponseTestDto.createInstance(user.get());
    }

    @Override
    public List<UserResponseTestDto> findAllTest() throws Exception{
        return userRepository.searchAll();
    }

}
