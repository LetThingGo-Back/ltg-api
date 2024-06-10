package com.letthinggo.ltgapi.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
public class GroupCodeCreateRequest {
    @Schema(title="그룹코드", example = "10001")
    @NotBlank
    @Size(max=6)
    private String groupCode;

    @Schema(title="그룹코드명", example = "테스트 코드")
    @NotBlank
    @Size(max=150)
    private String groupCodeName;

    @Schema(title="관리항목설명1", example = "")
    @Size(max=150)
    private String mngDes1;

    @Schema(title="관리항목설명2", example = "")
    @Size(max=150)
    private String mngDes2;

    @Schema(title="관리항목설명3", example = "")
    @Size(max=150)
    private String mngDes3;

    @Schema(title="관리항목설명4", example = "")
    @Size(max=150)
    private String mngDes4;

    @Schema(title="그룹코드설명", example = "테스트 그룹 코드 입니다.")
    @Size(max=500)
    private String description;

    @Schema(title="사용여부", example = "Y")
    @NotBlank
    private String useYn;

    @Builder
    public GroupCodeCreateRequest(String groupCode, String groupCodeName, String mngDes1, String mngDes2, String mngDes3, String mngDes4, String description, String useYn) {
        this.groupCode = groupCode;
        this.groupCodeName = groupCodeName;
        this.mngDes1 = mngDes1;
        this.mngDes2 = mngDes2;
        this.mngDes3 = mngDes3;
        this.mngDes4 = mngDes4;
        this.description = description;
        this.useYn = useYn;
    }
}
