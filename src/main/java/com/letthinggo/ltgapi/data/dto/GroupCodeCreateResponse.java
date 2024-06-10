package com.letthinggo.ltgapi.data.dto;

import com.letthinggo.ltgapi.data.entity.GroupCode;
import lombok.Builder;
import lombok.Data;

@Data
public class GroupCodeCreateResponse {
    private String groupCode;
    private String groupCodeName;
    private String mngDes1;
    private String mngDes2;
    private String mngDes3;
    private String mngDes4;
    private String description;
    private String useYn;

    @Builder
    public GroupCodeCreateResponse(String groupCode, String groupCodeName, String mngDes1, String mngDes2, String mngDes3, String mngDes4, String description, String useYn) {
        this.groupCode = groupCode;
        this.groupCodeName = groupCodeName;
        this.mngDes1 = mngDes1;
        this.mngDes2 = mngDes2;
        this.mngDes3 = mngDes3;
        this.mngDes4 = mngDes4;
        this.description = description;
        this.useYn = useYn;
    }

    public static GroupCodeCreateResponse createResponse(GroupCode groupCode){
        return GroupCodeCreateResponse.builder().groupCode(groupCode.getGroupCode())
                .groupCodeName(groupCode.getGroupCodeName())
                .mngDes1(groupCode.getMngDes1())
                .mngDes2(groupCode.getMngDes2())
                .mngDes3(groupCode.getMngDes3())
                .mngDes4(groupCode.getMngDes4())
                .description(groupCode.getDescription())
                .useYn(groupCode.getUseYn())
                .build();
    }
}
