package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.exception.CommonException;
import com.letthinggo.ltgapi.exception.ErrorCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProgressCode {

    //신청 받는 중, 일정 조율 중, 나눔 예정,  나눔 완료, 취소됨
    WAITING("1","신청받는중","PROGRESSING"),
    COORDINATING("2","일정조율중","COORDINATING"),
    PLANNING("3", "나눔예정중","PLANNING"),
    COMPLETED("C", "나눔완료", "COMPLETED"),
    CANCEL("X", "나눔취소", "CANCEL")
    ;

    private String code;
    private String codeKorName;
    private String codeEngName;

    ProgressCode(String code, String codeKorName, String codeEngName) {
        this.code = code;
        this.codeKorName = codeKorName;
        this.codeEngName = codeEngName;
    }

    public static ProgressCode fromCode(String dbData) {
        return Arrays.stream(ProgressCode.values())
                .filter(v->v.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()->new CommonException(ErrorCode.INVALID_INPUT_VALUE))
                ;

    }



}

