package com.letthinggo.ltgapi.domain.code.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CodeCreateRequest {
    @Schema(title="그룹코드", example = "TEST01")
    @NotBlank
    @Size(max=6)
    private String groupCode;
    List<CodeDto> codes = new ArrayList<>();

    @Builder
    public CodeCreateRequest(String groupCode, List<CodeDto> codes) {
        this.groupCode = groupCode;
        this.codes = codes;
    }
}
