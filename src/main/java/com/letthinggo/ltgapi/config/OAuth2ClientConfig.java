package com.letthinggo.ltgapi.config;

import com.letthinggo.ltgapi.service.CustomOAuth2UserService;
import com.letthinggo.ltgapi.service.CustomOidcUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class OAuth2ClientConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOidcUserService customOidcUserService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/test/**");
    }

    @Bean
    SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/api/users")
                .hasRole("OAUTH2_USER")
                .requestMatchers("/api/oidc")
                .hasRole("OIDC_USER")
                .requestMatchers("/")
                .permitAll()
                .anyRequest().authenticated());
        http.oauth2Login(oauth2 -> oauth2.userInfoEndpoint(
                userInfoEndpointConfig -> userInfoEndpointConfig
                        .userService(customOAuth2UserService)  // OAuth2
                        .oidcUserService(customOidcUserService)));  // OpenID Connect
        http.logout(logout -> logout.logoutSuccessUrl("/"));
//        http.userDetailsService(customUserDetailsService);  // Form
//        http.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
        return http.build();
    }

    @Bean
    public GrantedAuthoritiesMapper customAuthorityMapper(){
        return new CustomAuthorityMapper();
    }
}
