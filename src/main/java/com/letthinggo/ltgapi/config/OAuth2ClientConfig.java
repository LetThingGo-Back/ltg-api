package com.letthinggo.ltgapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letthinggo.ltgapi.exception.CustomAccessDeniedHandler;
import com.letthinggo.ltgapi.exception.CustomAuthenticationEntryPoint;
import com.letthinggo.ltgapi.jwt.JwtFilter;
import com.letthinggo.ltgapi.social.handler.CustomSuccessHandler;
import com.letthinggo.ltgapi.social.service.CustomOAuth2UserService;
import com.letthinggo.ltgapi.social.service.CustomOidcUserService;
import com.letthinggo.ltgapi.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class OAuth2ClientConfig {
    private final JwtUtil jwtUtil;

    private final ObjectMapper objectMapper;
    private final CustomSuccessHandler customSuccessHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOidcUserService customOidcUserService;

    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
//                 "/test/users/**",
                "/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs/**", "/clauseNew/**");
    }
    // CORS 설정
    CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedOriginPatterns(Collections.singletonList("*")); // 허용할 origin
            config.setAllowedOrigins(Collections.singletonList("*"));
            config.setAllowCredentials(true);
            config.setExposedHeaders(Collections.singletonList("Set-Cookie"));
            config.setExposedHeaders(Collections.singletonList("Authorization"));
            return config;
        };
    }
    @Bean
    SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
        http.cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
            .csrf(AbstractHttpConfigurer::disable) //csrf disable
            .formLogin(AbstractHttpConfigurer::disable) //From 로그인 방식 disable
            .httpBasic(HttpBasicConfigurer::disable); //HTTP Basic 인증 방식 disable
        http.addFilterBefore(new JwtFilter(jwtUtil, objectMapper), UsernamePasswordAuthenticationFilter.class);
        http.authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/test/users/**").hasRole("ADMIN")
//                        .requestMatchers("/test/users").hasRole("USER")
//                .requestMatchers("/", "/v1/reissue", "/v1/oauth/**", "/**") // TODO: 추후에 다시 수정
                .requestMatchers("/", "/v1/reissue", "/v1/oauth/**") // TODO: 추후에 다시 수정
                .permitAll()
                .anyRequest().authenticated())
                .exceptionHandling(c ->c.authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
            ;
        http.oauth2Login(oauth2 -> oauth2
                .successHandler(customSuccessHandler)
                .userInfoEndpoint(
                userInfoEndpointConfig -> userInfoEndpointConfig
                        .userService(customOAuth2UserService)  // OAuth2
                        .oidcUserService(customOidcUserService)));  // OpenID Connect
        http.sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

}
