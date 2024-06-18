package com.letthinggo.ltgapi.data.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

@Data
public class GroupCodeSearchResponse {
    private String groupCode;
    private String groupCodeName;
    private String mngDes1;
    private String mngDes2;
    private String mngDes3;
    private String mngDes4;
    private String description;
    private String useYn;

    @Builder
    @QueryProjection
    public GroupCodeSearchResponse(String groupCode, String groupCodeName, String mngDes1, String mngDes2, String mngDes3, String mngDes4, String description, String useYn) {
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
