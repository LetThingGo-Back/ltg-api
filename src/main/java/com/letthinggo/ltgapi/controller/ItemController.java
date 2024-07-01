package com.letthinggo.ltgapi.controller;

import com.letthinggo.ltgapi.data.dto.ItemDto;
import com.letthinggo.ltgapi.response.ApiCommonResponse;
import com.letthinggo.ltgapi.service.ItemService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
@Slf4j
@Tag(name="item-controller", description ="나눔물품 등록을 위한 컨트롤러 입니다.")
public class ItemController {

    private final ItemService itemService;

    @Operation(summary = "나눔 등록 API", description = "나눔 물품을 등록 위한 물품 정보를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/items")
    public ResponseEntity addItem(@RequestBody ItemDto.CreateRequest createRequest, Authentication authentication,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.info("나눔물품 시작");
        Long userId = authentication == null ? createRequest.getUserId() : ((CustomOAuth2User) authentication.getPrincipal()).getUserId();
        ItemDto.CreateResponse result = itemService.addItem(userId,createRequest);
        EntityModel entityModel = EntityModel.of(ApiCommonResponse.createSuccess(result));
        return ResponseEntity.ok(entityModel);
    }
}
