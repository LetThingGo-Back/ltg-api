package com.letthinggo.ltgapi.social.service;

import com.letthinggo.ltgapi.social.converters.ProviderUserRequest;
import com.letthinggo.ltgapi.social.dto.ProviderUser;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

// TODO: 추후에 oidc 방식도 다시 확인
@Service
public class CustomOidcUserService extends AbstractOAuth2UserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService = new OidcUserService();
        OidcUser oidcUser = oidcUserService.loadUser(userRequest);
        ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration,oidcUser);
        ProviderUser providerUser = super.providerUser(providerUserRequest);
        super.register(providerUser, userRequest);

        return oidcUser;
    }
}