package com.letthinggo.ltgapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;

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
    public OpenAPI api(){
        // SecuritySecheme명
        String jwtSchemeName = "JwtAuth";
        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                        .scheme("Bearer")
                        .bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER)
                        .bearerFormat("Authorization"));
        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(components);
    }
//    @Bean
//    public GroupedOpenApi customTestOpenApi(){
//        String[] paths = {"/test/**"};
//        return GroupedOpenApi.builder()
//                .group("테스트 API")
//                .pathsToMatch(paths)
//                .build();
//    }
}
