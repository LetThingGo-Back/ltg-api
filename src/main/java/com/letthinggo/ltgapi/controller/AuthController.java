package com.letthinggo.ltgapi.controller;

import com.letthinggo.ltgapi.data.dto.TokenResponseDto;
import com.letthinggo.ltgapi.data.dto.TokenRequestDto;
import com.letthinggo.ltgapi.jwt.JwtUtil;
import com.letthinggo.ltgapi.response.ApiCommonResponse;
import com.letthinggo.ltgapi.service.SocialLoginService;
import com.letthinggo.ltgapi.service.UserService;
import com.letthinggo.ltgapi.util.CookieUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.letthinggo.ltgapi.util.CookieUtil.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
@Tag(name="auth-controller", description = "소셜 로그인 서비스를 위한 컨트롤러입니다.")
public class AuthController {
    private final SocialLoginService socialLoginService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

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
        response.setHeader("Authorization", "Bearer " + token.getAccessToken());
        response.addCookie(createCookie("refreshToken", token.getRefreshToken()));
        return ResponseEntity.ok(entityModel);
    }

    @Operation(summary = "토큰 재발급 API", description = "access토큰 만료되면 refresh 토큰을 이용하여 토큰을 재발급합니다.")
    @PostMapping("/v1/reissue")
    public ResponseEntity reissue(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String refreshToken = getRefreshToken(request.getCookies());
        TokenResponseDto token = socialLoginService.reissue(refreshToken);
        EntityModel entityModel = EntityModel.of(ApiCommonResponse.createSuccessWithNoContent());
        response.setHeader("Authorization", "Bearer " + token.getAccessToken());
        response.addCookie(createCookie("refreshToken", token.getRefreshToken()));
        return ResponseEntity.ok(entityModel);
    }
}
