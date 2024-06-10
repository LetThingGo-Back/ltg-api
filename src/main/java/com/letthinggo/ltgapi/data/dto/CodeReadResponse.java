package com.letthinggo.ltgapi.data.dto;

import com.letthinggo.ltgapi.data.entity.Code;
import com.letthinggo.ltgapi.data.entity.GroupCode;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CodeReadResponse {
    private String groupCode;
    private String groupCodeName;

    private List<CodeDto> codeDtos = new ArrayList<>();

    @Builder
    public CodeReadResponse(String groupCode, String groupCodeName, List<CodeDto> codeDtos) {
        this.groupCode = groupCode;
        this.groupCodeName = groupCodeName;
        this.codeDtos = codeDtos;
    }

    public static CodeReadResponse createCodeReadResponse(List<GroupCode> groupCodesOut) {
        return CodeReadResponse.builder()
                .groupCode(groupCodesOut.get(0).getGroupCode())
                .groupCodeName(groupCodesOut.get(0).getGroupCodeName())
//                .codeDtos(codeDtos)
                .build();
    }
}
