package com.letthinggo.ltgapi.domain.userAddress.data.entity;


import com.letthinggo.ltgapi.global.exception.CommonException;
import com.letthinggo.ltgapi.global.exception.ErrorCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AddrTypeCode {

    H ("H","집근처","HOME"),
    W ("W","회사근처","WORK"),
    E ("E","기타","ETC")
    ;

    private String code;
    private String codeKorName;
    private String codeEngName;

    AddrTypeCode(String code, String codeKorName, String codeEngName) {
        this.code=code;
        this.codeKorName=codeKorName;
        this.codeEngName=codeEngName;
    }

    public static AddrTypeCode fromCode(String dbData){
        return Arrays.stream(AddrTypeCode.values())
                .filter(v->v.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new CommonException(ErrorCode.INVALID_INPUT_VALUE)) // TODO: 추후 변경
                ;
    }
}
