package com.letthinggo.ltgapi.social.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NaverUser extends OAuth2ProviderUser{

    public NaverUser(Attributes attributes, OAuth2User oAuth2User, ClientRegistration clientRegistration){
        super(attributes.getSubAttributes(), oAuth2User, clientRegistration);
    }

    @Override
    public String getId() {
        return (String)getAttributes().get("id");
    }

    @Override
    public String getUsername() {
        return new StringBuilder()
                .append(getProvider()).append("_")
                .append((String)getAttributes().get("id")).toString();
    }
    @Override
    public String getNickname() {
        return (String)getAttributes().get("nickname");
    }

    @Override
    public Map<String, String>  getAllowedServiceTerms(String accessToken) {
        String uri = "https://openapi.naver.com/v1/nid/agreement";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        Map<String, Object> resultMap = null;
        try {
            resultMap = objectMapper.readValue(result.getBody(), HashMap.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if(!"success".equals(resultMap.get("result"))){
            return null;
        }
        List<Map<String, String>> agreementsInfo = (List<Map<String, String>>) resultMap.get("agreementInfos");
        Map<String, String>  userAgreements = new HashMap<>();
        for( Map<String, String> info : agreementsInfo){
            userAgreements.put(info.get("termCode"), "Y");
        }
        return userAgreements;
    }
}
