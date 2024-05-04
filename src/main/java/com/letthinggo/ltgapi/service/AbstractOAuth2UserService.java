package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.UserCreateRequest;
import com.letthinggo.ltgapi.data.entity.SocialLogin;
import com.letthinggo.ltgapi.data.entity.SocialLoginCode;
import com.letthinggo.ltgapi.data.entity.Users;
import com.letthinggo.ltgapi.data.repository.SocialLoginRepository;
import com.letthinggo.ltgapi.data.repository.UserRepository;
import com.letthinggo.ltgapi.oauth2.GoogleUser;
import com.letthinggo.ltgapi.oauth2.NaverUser;
import com.letthinggo.ltgapi.oauth2.ProviderUser;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public abstract class AbstractOAuth2UserService {
    @Autowired
    private UserService userService;
    @Autowired
    private SocialLoginRepository socialLoginRepository;

    public void register(ProviderUser providerUser, OAuth2UserRequest userRequest){
        log.debug("여기오나?111", providerUser.getProvider());
        SocialLogin socialLogin = socialLoginRepository.findBySocialCodeAndExternalId(SocialLoginCode.valueOf(providerUser.getProvider()), providerUser.getId());

        if(socialLogin == null){
            userService.createUser(new UserCreateRequest(providerUser));
        }else{
            log.debug("userRequest = ", userRequest);
        }
    }

    public ProviderUser providerUser(ClientRegistration clientRegistration, OAuth2User oAuth2User){
        String registrationId = clientRegistration.getRegistrationId();
        if(registrationId.equals("google")){
            return new GoogleUser(oAuth2User,clientRegistration);
        }
        else if(registrationId.equals("naver")){
            return new NaverUser(oAuth2User,clientRegistration);
        }else if(registrationId.equals("kakao")){
            return null;
        }
        return null;
    }
}
