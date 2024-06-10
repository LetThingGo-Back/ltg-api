package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.entity.common.BaseDateTimeBy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "CODE")
public class Code extends BaseDateTimeBy {
    @EmbeddedId
    private CodePk codePk;

    @Column(name="CODE_KOR_NAME")
    private String codeKorName;
    @Column(name="GROUP_ENG_NAME")
    private String codeEngName;
    @Column(name="MNG_ITEM1")
    private String mngItem1;
    @Column(name="MNG_ITEM2")
    private String mngItem2;
    @Column(name="MNG_ITEM3")
    private String mngItem3;
    @Column(name="MNG_ITEM4")
    private String mngItem4;
    @Column(name="DESCRIOPTION")
    private String description;
    @Column(name="USER_YN")
    private String useYn;
    @Column(name="CODE_SEQ")
    private String codeSeq;
}
