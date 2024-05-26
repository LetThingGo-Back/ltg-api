package com.letthinggo.ltgapi.service.impl;

import com.letthinggo.ltgapi.data.dto.UserDto;
import com.letthinggo.ltgapi.data.dto.UserResponseDto;
import com.letthinggo.ltgapi.data.dto.UserResponseTestDto;
import com.letthinggo.ltgapi.data.dto.UserRequestTestDto;
import com.letthinggo.ltgapi.data.entity.SocialLogin;
import com.letthinggo.ltgapi.data.entity.Terms;
import com.letthinggo.ltgapi.data.entity.UserTerms;
import com.letthinggo.ltgapi.data.entity.Users;
import com.letthinggo.ltgapi.data.repository.SocialLoginRepository;
import com.letthinggo.ltgapi.data.repository.TermsRepository;
import com.letthinggo.ltgapi.data.repository.UserRepository;
import com.letthinggo.ltgapi.data.repository.UserTermsRepository;
import com.letthinggo.ltgapi.exception.CommonException;
import com.letthinggo.ltgapi.exception.ErrorCode;
import com.letthinggo.ltgapi.exception.UserNotFoundException;
import com.letthinggo.ltgapi.service.UserService;
import com.letthinggo.ltgapi.util.NicknameGenerator;
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

    private final UserTermsRepository userTermsRepository;
    private final TermsRepository termsRepository;
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
    public UserResponseDto findOne(Long id) throws Exception{
        Optional<Users> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }
        return UserResponseDto.builder().user(user.get()).build();
    }

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) {
        String uniqueNickname = generateUniqueNickname();
        userDto.setNickname(uniqueNickname);
        // 1. 사용자 정보 생성
        Users user = Users.createUsers(userDto);
        userRepository.save(user);
        // 2. 소셜로그인 정보 생성
        SocialLogin socialLogin = SocialLogin.createSocialLogin(userDto, user);
        socialLoginRepository.save(socialLogin);
        userDto.setUserId(user.getId());
        List<Terms> terms = termsRepository.findByUseYn ("Y");
        // 3. 약관 동의 정보 생성
        if(userDto.getAllowedServiceTerms()!= null){
            List<UserTerms> userTerms = UserTerms.createUserTerms(userDto.getAllowedServiceTerms(), user, terms);
            userTermsRepository.saveAll(userTerms);
        }

        return userDto;
    }
    public String generateUniqueNickname() {
        String nickname;
        do {
            nickname = NicknameGenerator.generateRandomNickname();
        } while (userRepository.existsByNickname(nickname));
        return nickname;
    }


}
