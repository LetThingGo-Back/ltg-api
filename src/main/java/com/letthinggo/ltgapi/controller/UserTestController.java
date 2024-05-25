package com.letthinggo.ltgapi.controller;

import com.letthinggo.ltgapi.data.dto.UserResponseTestDto;
import com.letthinggo.ltgapi.response.ApiCommonResponse;
import com.letthinggo.ltgapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    private final UserService userService;
    @GetMapping("/users")
    public ResponseEntity retrieveAllUsers(){
        List<UserResponseTestDto> testDto = userService.findAllTest();
        EntityModel entityModel = EntityModel.of(ApiCommonResponse.createSuccess(testDto));
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkTo.withSelfRel());
        return ResponseEntity.ok(entityModel);
    }
    @Operation(summary = "사용자 정보 조회 테스트 API", description = "아이디로 사용자 정보 조회를 합니다.")
    @GetMapping("/users/{id}")
    public ResponseEntity retrieveUserTest(@Parameter(description = "사용자 ID", required = true, example = "16")
                                                                    @PathVariable Long id) throws Exception{
        EntityModel entityModel = EntityModel.of(ApiCommonResponse.createSuccess(userService.findUserTest(id)));
        WebMvcLinkBuilder linTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linTo.withRel("all-users")); // all-users -> http://localhost:8080/users
        return ResponseEntity.ok(entityModel);
    }

}
