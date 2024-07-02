package com.letthinggo.ltgapi.global.social.service;

import com.letthinggo.ltgapi.global.social.converters.ProviderUserConverter;
import com.letthinggo.ltgapi.global.social.converters.ProviderUserRequest;
import com.letthinggo.ltgapi.domain.user.data.dto.UserDto;
import com.letthinggo.ltgapi.domain.auth.data.entity.SocialLogin;
import com.letthinggo.ltgapi.domain.auth.data.entity.SocialLoginCode;
import com.letthinggo.ltgapi.domain.auth.data.repository.AuthRepository;
import com.letthinggo.ltgapi.global.social.dto.ProviderUser;
import com.letthinggo.ltgapi.domain.user.service.UserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public abstract class AbstractOAuth2UserService {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthRepository socialLoginRepository;

    @Autowired
    private ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;
    public UserDto register(ProviderUser providerUser, OAuth2UserRequest userRequest){
        SocialLogin socialLogin = socialLoginRepository.findBySocialCodeAndExternalId(SocialLoginCode.valueOf(providerUser.getProvider()), providerUser.getId());
        String accessToken = "";
        UserDto userDto = new UserDto(providerUser,  userRequest.getAccessToken().getTokenValue());
        if(socialLogin == null){
            return userService.createUser(userDto);
        }

        //이미 가입된 경우
        userDto.setUserId(socialLogin.getUser().getId());
        return userDto;
    }

    public ProviderUser providerUser(ProviderUserRequest providerUserRequest){
        return providerUserConverter.convert(providerUserRequest);
    }
}
