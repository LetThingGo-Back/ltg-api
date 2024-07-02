package com.letthinggo.ltgapi.domain.code.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CodeSearchRequest {
    @Schema(title="사용여부", example = "Y")
    @Nullable
    @Size(max=1)
    private String useYn;

    @Schema(title="코드한글명", example = "렛띵고")
    @Nullable
    @Size(max=150)
    private String codeKorName;

    @Schema(title="코드영문명", example = "letthinggo")
    @Nullable
    @Size(max=150)
    private String codeEngName;

    @Schema(title="관리항목1", example = "")
    @Nullable
    @Size(max=150)
    private String mngItem1;

    @Schema(title="관리항목2", example = "")
    @Nullable
    @Size(max=150)
    private String mngItem2;

    @Schema(title="관리항목3", example = "")
    @Nullable
    @Size(max=150)
    private String mngItem3;

    @Schema(title="관리항목4", example = "")
    @Nullable
    @Size(max=150)
    private String mngItem4;

}
