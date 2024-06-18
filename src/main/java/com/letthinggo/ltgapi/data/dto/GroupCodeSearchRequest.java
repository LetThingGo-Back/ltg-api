package com.letthinggo.ltgapi.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class GroupCodeSearchRequest {
    @Schema(title="사용여부", example = "Y")
    @Nullable
    private String useYn;

    @Schema(title="그룹코드명", example = "물품상태")
    @Nullable
    private String groupCodeName;
}
