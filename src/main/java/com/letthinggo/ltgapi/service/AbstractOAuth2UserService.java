package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.converters.ProviderUserConverter;
import com.letthinggo.ltgapi.converters.ProviderUserRequest;
import com.letthinggo.ltgapi.data.dto.UserCreateRequest;
import com.letthinggo.ltgapi.data.entity.SocialLogin;
import com.letthinggo.ltgapi.data.entity.SocialLoginCode;
import com.letthinggo.ltgapi.data.repository.SocialLoginRepository;
import com.letthinggo.ltgapi.oauth2.ProviderUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public abstract class AbstractOAuth2UserService {
    @Autowired
    private  UserService userService;
    @Autowired
    private SocialLoginRepository socialLoginRepository;
    @Autowired
    private ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;
    public void register(ProviderUser providerUser, OAuth2UserRequest userRequest){
        SocialLogin socialLogin = socialLoginRepository.findBySocialCodeAndExternalId(SocialLoginCode.valueOf(providerUser.getProvider()), providerUser.getId());

        if(socialLogin == null){
            userService.createUser(new UserCreateRequest(providerUser));
        }else{
            log.debug("userRequest = ", userRequest);
        }
    }

    public ProviderUser providerUser(ProviderUserRequest providerUserRequest){
        return providerUserConverter.convert(providerUserRequest);
    }
}
