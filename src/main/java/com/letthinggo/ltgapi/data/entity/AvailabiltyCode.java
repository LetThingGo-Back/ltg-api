package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.exception.CommonException;
import com.letthinggo.ltgapi.exception.ErrorCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AvailabiltyCode {
    CONTRIBUTOR("C","Contributor"),
    APPLICATION("A","Application");

    private final String code;
    private final String availRegName;

    AvailabiltyCode(String code, String name) {
        this.code = code;
        this.availRegName = name;
    }

    public static AvailabiltyCode fromCode(String dbData){
        return Arrays.stream(AvailabiltyCode.values())
                .filter(v->v.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new CommonException(ErrorCode.INVAILD_AVAILABILTY_CODE, dbData));
    }
}
