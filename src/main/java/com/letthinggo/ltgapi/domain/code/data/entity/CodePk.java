package com.letthinggo.ltgapi.domain.code.data.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class CodePk implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="GROUP_CODE")
    private GroupCode groupCode;

    @Column(name="CODE")
    private String code;

    @Builder
    public CodePk(GroupCode groupCode, String code) {
        this.groupCode = groupCode;
        this.code = code;
    }

    public static CodePk createCodePk(GroupCode groupCode, String code) {
        return CodePk.builder().groupCode(groupCode).code(code).build();
    }
}
