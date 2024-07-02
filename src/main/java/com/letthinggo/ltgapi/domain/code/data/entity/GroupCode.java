package com.letthinggo.ltgapi.domain.code.data.entity;

import com.letthinggo.ltgapi.domain.code.data.dto.GroupCodeCreateRequest;
import com.letthinggo.ltgapi.global.audit.BaseDateTimeBy;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "GROUP_CODE")
public class GroupCode extends BaseDateTimeBy {
    @Id
    @Column(name="GROUP_CODE")
    private String groupCode;
    @Column(name="GROUP_CODE_NAME")
    private String groupCodeName;
    @Column(name="MNG_DES1")
    private String mngDes1;
    @Column(name="MNG_DES2")
    private String mngDes2;
    @Column(name="MNG_DES3")
    private String mngDes3;
    @Column(name="MNG_DES4")
    private String mngDes4;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="USE_YN")
    private String useYn;

    @OneToMany(mappedBy = "codePk.groupCode")
    private List<Code> codes;

    @Builder
    public GroupCode(String groupCode, String groupCodeName, String mngDes1, String mngDes2, String mngDes3, String mngDes4, String description, String useYn, List<Code> codes) {
        this.groupCode = groupCode;
        this.groupCodeName = groupCodeName;
        this.mngDes1 = mngDes1;
        this.mngDes2 = mngDes2;
        this.mngDes3 = mngDes3;
        this.mngDes4 = mngDes4;
        this.description = description;
        this.useYn = useYn;
        this.codes = codes;
    }

    public static GroupCode createGroupCode(GroupCodeCreateRequest dto) {
        return GroupCode.builder().groupCode(dto.getGroupCode().toUpperCase())
                .groupCodeName(dto.getGroupCodeName())
                .mngDes1(dto.getMngDes1())
                .mngDes2(dto.getMngDes2())
                .mngDes3(dto.getMngDes3())
                .mngDes4(dto.getMngDes4())
                .description(dto.getDescription())
                .useYn(dto.getUseYn())
                    .build();

    }
}
