package com.letthinggo.ltgapi.controller;

import com.letthinggo.ltgapi.data.dto.ApplicationCreateRequest;
import com.letthinggo.ltgapi.data.dto.ApplicationCreateResponse;
import com.letthinggo.ltgapi.data.dto.GroupCodeCreateRequest;
import com.letthinggo.ltgapi.data.dto.GroupCodeCreateResponse;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    }
    )
    @PostMapping("/v1/group-codes")
    public ResponseEntity saveGroupCode( @Valid @RequestBody GroupCodeCreateRequest groupCodeCreateRequest
            , HttpServletRequest request, HttpServletResponse response) throws Exception{
        GroupCodeCreateResponse rtnVo = codeService.createGroupCodes(groupCodeCreateRequest);
        EntityModel entityModel = EntityModel.of(ApiCommonResponse.createSuccess(rtnVo));
        return ResponseEntity.ok(entityModel);
    }
}
