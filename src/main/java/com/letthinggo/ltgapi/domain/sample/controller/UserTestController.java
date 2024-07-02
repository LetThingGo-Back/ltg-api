package com.letthinggo.ltgapi.domain.sample.controller;

import com.letthinggo.ltgapi.domain.sample.data.dto.UserResponseTestDto;
import com.letthinggo.ltgapi.domain.sample.service.UserTestService;
import com.letthinggo.ltgapi.global.response.ApiCommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
@Tag(name="user-test-controller", description = "테스트 컨트롤러입니다.")
public class UserTestController {

    private final UserTestService userService;
    @Operation(summary = "사용자 전체 조회 테스트 API", description = "전체 사용자의 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"
            )
    }
    )
    @GetMapping("/users")
    public ResponseEntity retrieveAllUsers() throws Exception{
        List<UserResponseTestDto> testDto = userService.findAllTest();
        EntityModel entityModel = EntityModel.of(ApiCommonResponse.createSuccess(testDto));
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkTo.withSelfRel());
        return ResponseEntity.ok(entityModel);
    }
    @Operation(summary = "사용자 정보 조회 테스트 API", description = "아이디로 사용자 정보 조회를 합니다.")
    @GetMapping("/users/{id}")
    public ResponseEntity retrieveUserTest(@Parameter(description = "사용자 ID", required = true, example = "19")
                                                                    @PathVariable Long id) throws Exception{
        EntityModel entityModel = EntityModel.of(ApiCommonResponse.createSuccess(userService.findUserTest(id)));
        WebMvcLinkBuilder linTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linTo.withRel("all-users")); // all-users -> http://localhost:8080/users
        return ResponseEntity.ok(entityModel);
    }

}
