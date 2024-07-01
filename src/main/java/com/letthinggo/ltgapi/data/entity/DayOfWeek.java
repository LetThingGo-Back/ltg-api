package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.exception.CommonException;
import com.letthinggo.ltgapi.exception.ErrorCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum DayOfWeek {

    MONDAY("1","월","MONDAY"),
    TUESDAY("2","화","TUESDAY"),
    WEDNESDAY("3","수","WEDNESDAY"),
    THURSDAY("4","목","THURSDAY"),
    FRIDAY("5","금","FRIDAY"),
    SATURDAY("6","토","SATURDAY"),
    SUNDAY("7","일","SUNDAY"),
    WEEKDAY("8","주중","WEEKDAY"),
    WEEKEND("9","주말","WEEKEND")
    ;

    private String code;
    private String codeKorName;
    private String codeEngName;

    DayOfWeek(String code, String codeKorName, String codeEngName) {
        this.code=code;
        this.codeKorName=codeKorName;
        this.codeEngName=codeEngName;
    }

    public static DayOfWeek fromCode(String dbData){
        return Arrays.stream(DayOfWeek.values())
                .filter(v->v.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new CommonException(ErrorCode.INVAILD_AVAILABILTY_CODE, dbData));
    }
}
