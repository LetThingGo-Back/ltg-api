package com.letthinggo.ltgapi.social.service;

import com.letthinggo.ltgapi.social.converters.ProviderUserRequest;
import com.letthinggo.ltgapi.data.dto.UserDto;
import com.letthinggo.ltgapi.social.dto.CustomOAuth2User;
import com.letthinggo.ltgapi.social.dto.ProviderUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends AbstractOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>  {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
        ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration,oAuth2User);
        ProviderUser providerUser = super.providerUser(providerUserRequest);
        // 회원가입하기
        UserDto userDto = super.register(providerUser, userRequest);
        return new CustomOAuth2User(userDto);
//        return oAuth2User;
    }


}
