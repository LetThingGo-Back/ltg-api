package com.letthinggo.ltgapi.data.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class CodeSearchResponse {
    private String groupCode;
    private String groupCodeName;

    private List<CodeDto> codes = new ArrayList<>();

    @Builder
    @QueryProjection
    public CodeSearchResponse(String groupCode, String groupCodeName, List<CodeDto> codes) {
        this.groupCode = groupCode;
        this.groupCodeName = groupCodeName;
        this.codes = codes;
    }
}
