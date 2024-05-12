package com.letthinggo.ltgapi.controller;

import com.letthinggo.ltgapi.data.dto.TokenResponseDto;
import com.letthinggo.ltgapi.data.dto.TokenRequestDto;
import com.letthinggo.ltgapi.jwt.JwtUtil;
import com.letthinggo.ltgapi.response.ApiCommonResponse;
import com.letthinggo.ltgapi.service.SocialLoginService;
import com.letthinggo.ltgapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
@Tag(name="auth-controller", description = "소셜 로그인 서비스를 위한 컨트롤러입니다.")
public class AuthController {
    private final SocialLoginService socialLoginService;
    private final UserService userService;
    private final JwtUtil jwtUtil;


//    @GetMapping("/api/users")
//    public Authentication users(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2User) {
//        System.out.println("authentication = " + authentication + ", oAuth2User = " + oAuth2User);
//        return authentication;
//    }
//
//    @GetMapping("/api/oidc") // 요청시 scope 에 openid 가 포함되어야 oidcUser 가 생성된다
//    public Authentication oidc(Authentication authentication, @AuthenticationPrincipal OidcUser oidcUser) {
//        System.out.println("authentication = " + authentication + ", oidcUser = " + oidcUser);
//        return authentication;
//    }
    @Operation(summary = "사용자 로그인 API", description = "로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")}
    )
    @Parameters({@Parameter(name = "provider", description = "Name of provider", example = "kakao, naver, google")})
    @PostMapping("/v1/oauth/{provider}")
    public ResponseEntity login(@PathVariable String provider, @Valid @RequestBody TokenRequestDto tokenRequestDto, HttpServletResponse response)  throws Exception{
        TokenResponseDto token = socialLoginService.login(provider, tokenRequestDto.getExternalToken());
        Long userId = jwtUtil.getUserId(token.getAccessToken());
        EntityModel entityModel = EntityModel.of(ApiCommonResponse.createSuccess(userService.findOne(userId)));
        response.setHeader("accessToken", "Bearer " + token.getAccessToken());
        response.addCookie(createCookie("refreshToken", token.getRefreshToken()));
        return ResponseEntity.ok(entityModel);
    }
    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(30 * 24 * 60 * 60); // 30일
        cookie.setHttpOnly(true);
        return cookie;
    }
}
