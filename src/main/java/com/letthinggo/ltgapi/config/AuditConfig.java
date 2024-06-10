package com.letthinggo.ltgapi.config;

import com.letthinggo.ltgapi.social.dto.CustomOAuth2User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Configuration
public class AuditConfig implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = 19L; //TODO: 테스트 계정
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            userId = ((CustomOAuth2User) authentication.getPrincipal()).getUserId();
        }
        return Optional.of(userId);
    }

}
