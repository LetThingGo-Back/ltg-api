package com.letthinggo.ltgapi.global.config;

import com.letthinggo.ltgapi.global.config.authority.CustomAuthorityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

@Configuration
public class OAuth2AppConfig {
    @Bean
    public GrantedAuthoritiesMapper customAuthorityMapper(){
        return new CustomAuthorityMapper();
    }
}
