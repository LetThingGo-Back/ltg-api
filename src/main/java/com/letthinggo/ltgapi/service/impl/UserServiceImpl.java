package com.letthinggo.ltgapi.service.impl;

import com.letthinggo.ltgapi.data.dto.UserDto;
import com.letthinggo.ltgapi.data.dto.UserResponse;
import com.letthinggo.ltgapi.data.dto.UserResponseTestDto;
import com.letthinggo.ltgapi.data.dto.UserRequestTestDto;
import com.letthinggo.ltgapi.data.entity.SocialLogin;
import com.letthinggo.ltgapi.data.entity.Users;
import com.letthinggo.ltgapi.data.repository.SocialLoginRepository;
import com.letthinggo.ltgapi.data.repository.UserRepository;
import com.letthinggo.ltgapi.exception.ErrorCode;
import com.letthinggo.ltgapi.exception.UserNotFoundException;
import com.letthinggo.ltgapi.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SocialLoginRepository socialLoginRepository;

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
    public List<UserResponseTestDto> findAllTest() {
        return userRepository.searchAll();
    }

    @Override
    public UserResponse findOne(Long id) throws Exception{
        Optional<Users> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new Exception(String.format("ID[%s] not found", id)); // TODO: 추후 다시 수정
        }
        return UserResponse.builder().user(user.get()).build();
    }

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) {
        Users user = Users.createUsers(userDto);
        userRepository.save(user);
        SocialLogin socialLogin = SocialLogin.createSocialLogin(userDto, user);
        socialLoginRepository.save(socialLogin);
        userDto.setUserId(user.getId());
        return userDto;
    }



}
