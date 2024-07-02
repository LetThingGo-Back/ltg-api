package com.letthinggo.ltgapi.domain.user.service.impl;

import com.letthinggo.ltgapi.domain.user.data.dto.UserDto;
import com.letthinggo.ltgapi.domain.user.data.dto.UserResponseDto;

import com.letthinggo.ltgapi.domain.auth.data.entity.SocialLogin;
import com.letthinggo.ltgapi.domain.terms.data.entity.Terms;
import com.letthinggo.ltgapi.domain.user.data.entity.UserTerms;
import com.letthinggo.ltgapi.domain.user.data.entity.Users;
import com.letthinggo.ltgapi.domain.auth.data.repository.AuthRepository;
import com.letthinggo.ltgapi.domain.terms.data.repository.TermsRepository;
import com.letthinggo.ltgapi.domain.user.data.repository.UserRepository;
import com.letthinggo.ltgapi.domain.user.data.repository.UserTermsRepository;
import com.letthinggo.ltgapi.global.exception.CommonException;
import com.letthinggo.ltgapi.global.exception.ErrorCode;
import com.letthinggo.ltgapi.domain.user.service.UserService;
import com.letthinggo.ltgapi.global.util.NicknameGenerator;
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
    private final AuthRepository authRepository;

    private final UserTermsRepository userTermsRepository;
    private final TermsRepository termsRepository;

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
        authRepository.save(socialLogin);
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
