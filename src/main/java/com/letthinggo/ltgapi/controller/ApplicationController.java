package com.letthinggo.ltgapi.controller;

import com.letthinggo.ltgapi.data.dto.ApplicationCreateRequest;
import com.letthinggo.ltgapi.data.dto.ApplicationCreateResponse;
import com.letthinggo.ltgapi.jwt.JwtUtil;
import com.letthinggo.ltgapi.response.ApiCommonResponse;
import com.letthinggo.ltgapi.service.ApplicationService;
import com.letthinggo.ltgapi.social.dto.CustomOAuth2User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
@Tag(name="application-controller", description = "나눔신청을 위한 컨트롤러입니다.")
public class ApplicationController {
    private final JwtUtil jwtUtil;
    private final ApplicationService applicationService;
    @Operation(summary = "나눔 신청 API", description = "물품 나눔을 받기 위한 신청 정보를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    }
    )
    @PostMapping("/v1/applications")
    public ResponseEntity saveRequest(@RequestBody ApplicationCreateRequest applicationCreateRequest, Authentication authentication
                                                    , HttpServletRequest request, HttpServletResponse response) throws Exception{
        Long userId = authentication == null ? applicationCreateRequest.getUserId() :  ((CustomOAuth2User) authentication.getPrincipal()).getUserId();
        ApplicationCreateResponse rtnVo = applicationService.createApplication(applicationCreateRequest, userId);
        EntityModel entityModel = EntityModel.of(ApiCommonResponse.createSuccess(rtnVo));
        return ResponseEntity.ok(entityModel);
    }
}
