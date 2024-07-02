package com.letthinggo.ltgapi.domain.code.controller;

import com.letthinggo.ltgapi.domain.code.data.dto.*;
import com.letthinggo.ltgapi.global.response.ApiCommonResponse;
import com.letthinggo.ltgapi.domain.code.service.CodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
@Tag(name="code-controller", description = "공통코드 관리 서비스를 위한 컨트롤러입니다.")
public class CodeController {
    private final CodeService codeService;
    @Operation(summary = "그룹코드 생성 API", description = "그룹코드 정보를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "40101", description = "이미 등록된 그룹코드입니다."),
    }
    )
    @PostMapping("/v1/group-codes" )
    public ResponseEntity createGroupCode( @Valid @RequestBody GroupCodeCreateRequest groupCodeCreateRequest) throws Exception{
        GroupCodeCreateResponse rtnVo = codeService.createGroupCode(groupCodeCreateRequest);
        EntityModel entityModel = EntityModel.of(ApiCommonResponse.createSuccess(rtnVo));
        URI selfLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{groupCode}").buildAndExpand(rtnVo.getGroupCode()).toUri();
        URI allLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                        .buildAndExpand(rtnVo.getGroupCode()).toUri();

        entityModel.add(Link.of(selfLocation.toString(), "self"));
        entityModel.add(Link.of(allLocation.toString(), "all-group-codes"));

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
    public ResponseEntity createCode( @Valid @RequestBody CodeCreateRequest codeCreateRequest) throws Exception{
        codeService.createCode(codeCreateRequest);
        EntityModel entityModel = EntityModel.of(ApiCommonResponse.createSuccess(null));
        Link linkToAllCodes = linkTo(methodOn(this.getClass()).retrieveCodes(codeCreateRequest.getGroupCode(), null, null))
                                .withRel("all-codes");
        entityModel.add(linkToAllCodes);
        return ResponseEntity.ok(entityModel);
    }

    @Operation(summary = "공통코드 조회 API", description = "특정 그룹코드의 공통코드 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "40103", description = "그룹코드가 존재하지 않습니다."),
    }
    )
    @GetMapping({"/v1/group-codes/{groupCode}/codes", "/v1/group-codes/{groupCode}/codes/{code}" })
    public ResponseEntity retrieveCodes( @PathVariable String groupCode
            , @PathVariable(required = false) String code
            , @Valid @Nullable @ModelAttribute CodeSearchRequest codeRequest)throws Exception{
        CodeSearchResponse codeSearchResponse = codeService.retrieveCode(groupCode, code, codeRequest);
        EntityModel entityModel = EntityModel.of(ApiCommonResponse.createSuccess(codeSearchResponse));
        return ResponseEntity.ok(entityModel);
    }

    @Operation(summary = "그룹 공통코드 조회 API", description = "그룹코드 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    }
    )
    @GetMapping({"/v1/group-codes", "/v1/group-codes/{groupCode}" })
    public ResponseEntity retrieveGroupCodes( @PathVariable(required = false) String groupCode,
                                         @Valid @Nullable @ModelAttribute GroupCodeSearchRequest groupCodeRequest)throws Exception{
        List<GroupCodeSearchResponse> groupCodeSearchResponse = codeService.retrieveGroupCode(groupCode, groupCodeRequest);
        EntityModel entityModel = EntityModel.of(ApiCommonResponse.createSuccess(groupCodeSearchResponse));
        return ResponseEntity.ok(entityModel);
    }
}
