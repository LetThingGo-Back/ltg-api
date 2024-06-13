package com.letthinggo.ltgapi.data.dto;

import lombok.Data;

@Data
public class CodeSearchRequest {
    private String useYn;
    private String codeKorName;
    private String codeEngName;
    private String mngItem1;
    private String mngItem2;
    private String mngItem3;
    private String mngItem4;

}
