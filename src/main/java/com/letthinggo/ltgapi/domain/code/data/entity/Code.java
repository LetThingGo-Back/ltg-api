package com.letthinggo.ltgapi.domain.code.data.entity;

import com.letthinggo.ltgapi.domain.code.data.dto.CodeCreateRequest;
import com.letthinggo.ltgapi.global.audit.BaseDateTimeBy;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "CODE")
public class Code extends BaseDateTimeBy {
    @EmbeddedId
    private CodePk codePk;

    @Column(name="CODE_KOR_NAME")
    private String codeKorName;
    @Column(name="CODE_ENG_NAME")
    private String codeEngName;
    @Column(name="MNG_ITEM1")
    private String mngItem1;
    @Column(name="MNG_ITEM2")
    private String mngItem2;
    @Column(name="MNG_ITEM3")
    private String mngItem3;
    @Column(name="MNG_ITEM4")
    private String mngItem4;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="USE_YN")
    private String useYn;
    @Column(name="CODE_SEQ")
    private Integer codeSeq;

    @Builder
    public Code(CodePk codePk, String codeKorName, String codeEngName, String mngItem1, String mngItem2, String mngItem3, String mngItem4, String description, String useYn, Integer codeSeq) {
        this.codePk = codePk;
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

    public static List<Code> createCodes(CodeCreateRequest request, GroupCode groupCode ){
        return request.getCodes().stream().map(c-> Code.builder()
                        .codePk(CodePk.createCodePk(groupCode, c.getCode()))
                        .codeKorName(c.getCodeKorName())
                        .codeEngName(c.getCodeEngName())
                        .mngItem1(c.getMngItem1())
                        .mngItem2(c.getMngItem2())
                        .mngItem3(c.getMngItem3())
                        .mngItem4(c.getMngItem4())
                        .description(c.getDescription())
                        .useYn(c.getUseYn())
                        .codeSeq(c.getCodeSeq())
                        .build())
                .collect(Collectors.toList());
    }
}
