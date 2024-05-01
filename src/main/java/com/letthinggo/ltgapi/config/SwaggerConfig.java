package com.letthinggo.ltgapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "LetThingGo API 명세서",
                     description= "렛띵고 RESTful API 명세서 입니다.",
                       version = "v1.0.0")
)
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi customTestOpenApi(){
        String[] paths = {"/users/**"};
        return GroupedOpenApi.builder()
                .group("일반 사용자를 위한 User 도메인에 대한 API")
                .pathsToMatch(paths)
                .build();
    }
}
