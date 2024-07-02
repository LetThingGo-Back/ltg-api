package com.letthinggo.ltgapi.domain.code.data.dto;

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
    private String mngDes1;
    private String mngDes2;
    private String mngDes3;
    private String mngDes4;
    private String description;
    private String useYn;
    private List<CodeDto> codes = new ArrayList<>();

    @Builder
    @QueryProjection
    public CodeSearchResponse(String groupCode, String groupCodeName, String mngDes1, String mngDes2, String mngDes3, String mngDes4, String description, String useYn, List<CodeDto> codes) {
        this.groupCode = groupCode;
        this.groupCodeName = groupCodeName;
        this.mngDes1 = mngDes1;
        this.mngDes2 = mngDes2;
        this.mngDes3 = mngDes3;
        this.mngDes4 = mngDes4;
        this.description = description;
        this.useYn = useYn;
        this.codes = codes.isEmpty() || (codes.size() == 1 && codes.get(0).getCode() == null)  ? new ArrayList<>() : codes;
    }
}
