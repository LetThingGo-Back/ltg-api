package com.letthinggo.ltgapi.controller;

import com.letthinggo.ltgapi.data.dto.*;
import com.letthinggo.ltgapi.response.ApiCommonResponse;
import com.letthinggo.ltgapi.service.CodeService;
import com.letthinggo.ltgapi.social.dto.CustomOAuth2User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
@Tag(name="common-controller", description = "공통 서비스를 위한 컨트롤러입니다.")
public class CommonController {
    private final CodeService codeService;
    @Operation(summary = "그룹코드 생성 API", description = "그룹코드 정보를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "40101", description = "이미 등록된 그룹코드입니다."),
    }
    )
    @PostMapping("/v1/group-codes")
    public ResponseEntity createGroupCode( @Valid @RequestBody GroupCodeCreateRequest groupCodeCreateRequest
            , HttpServletRequest request, HttpServletResponse response) throws Exception{
        GroupCodeCreateResponse rtnVo = codeService.createGroupCode(groupCodeCreateRequest);
        EntityModel entityModel = EntityModel.of(ApiCommonResponse.createSuccess(rtnVo));
        return ResponseEntity.ok(entityModel);
    }

    @Operation(summary = "공통코드 생성 API", description = "공통코드 정보를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "40102", description = "동일한 그룹코드에 공통코드는 중복될 수 없습니다."),
            @ApiResponse(responseCode = "40103", description = "그룹코드가 존재하지 않습니다."),
            @ApiResponse(responseCode = "40104", description = "이미 등록된 공통코드입니다."),
    }
    )
    @PostMapping("/v1/codes")
    public ResponseEntity createCode( @Valid @RequestBody CodeCreateRequest codeCreateRequest
            , HttpServletRequest request, HttpServletResponse response) throws Exception{
        codeService.createCode(codeCreateRequest);
        EntityModel entityModel = EntityModel.of(ApiCommonResponse.createSuccess(null));
        return ResponseEntity.ok(entityModel);
    }

    @Operation(summary = "공통코드 조회 API", description = "공통코드 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    }
    )
    @PostMapping("/v1/group-codes/{groupCode}/codes/{code}")
    public ResponseEntity retrieveCode( @PathVariable String groupCode, @PathVariable String code, HttpServletRequest request, HttpServletResponse response) throws Exception{
        CodeReadResponse codeReadResponse = codeService.retrieveCode(groupCode, code);
        EntityModel entityModel = EntityModel.of(ApiCommonResponse.createSuccess(codeReadResponse));
        return ResponseEntity.ok(entityModel);
    }
}
