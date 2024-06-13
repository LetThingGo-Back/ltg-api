package com.letthinggo.ltgapi.data.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;


@Data
public class CodeDto {

    @Schema(title="공통코드", example = "L")
//    @NotBlank
    @Size(max=150)
    private String code;

    @Schema(title="코드한글명", example = "렛띵고")
    @Size(max=150)
    private String codeKorName;

    @Schema(title="코드영문명", example = "letthinggo")
    @Size(max=150)
    private String codeEngName;

    @Schema(title="관리항목1", example = "")
    @Size(max=150)
    private String mngItem1;

    @Schema(title="관리항목2", example = "")
    @Size(max=150)
    private String mngItem2;

    @Schema(title="관리항목3", example = "")
    @Size(max=150)
    private String mngItem3;

    @Schema(title="관리항목4", example = "")
    @Size(max=150)
    private String mngItem4;

    @Schema(title="코드설명", example = "테스트 코드 입니다.")
    @Size(max=500)
    private String description;

    @Schema(title="사용여부", example = "Y")
//    @NotBlank
    private String useYn;

    @Schema(title="순서", example = "1")
//    @NotBlank
    private Integer codeSeq;

    @Builder
    @QueryProjection
    public CodeDto(String code, String codeKorName, String codeEngName, String mngItem1, String mngItem2, String mngItem3, String mngItem4, String description, String useYn, Integer codeSeq) {
        this.code = code;
        this.codeKorName = codeKorName;
        this.codeEngName = codeEngName;
        this.mngItem1 = mngItem1;
        this.mngItem2 = mngItem2;
        this.mngItem3 = mngItem3;
        this.mngItem4 = mngItem4;
        this.description = description;
        this.useYn = useYn;
        this.codeSeq = codeSeq;
    }
    
}
