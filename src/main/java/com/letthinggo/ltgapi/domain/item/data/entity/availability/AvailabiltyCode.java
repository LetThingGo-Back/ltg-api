package com.letthinggo.ltgapi.domain.item.data.entity.availability;

import com.letthinggo.ltgapi.global.exception.CommonException;
import com.letthinggo.ltgapi.global.exception.ErrorCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AvailabiltyCode {
    CONTRIBUTOR("C","Contributor"),
    APPLICANT("A","APPLICANT");

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

