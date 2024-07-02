package com.letthinggo.ltgapi.domain.application.data.entity;

import com.letthinggo.ltgapi.global.exception.CommonException;
import com.letthinggo.ltgapi.global.exception.ErrorCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ApplicationStatus {
    WAITING("1", "수락대기중","WAITING"),
    COORDINATING("2", "일정조율중","COORDINATING"),
    PLANNING("3", "나눔예정중","PLANNING"),
    COMPLETED("C", "나눔완료", "COMPLETED"),
    CANCEL("X", "나눔취소", "CANCEL")
    ;
    private String code;
    private String codeKorName;
    private String codeEngName;

    ApplicationStatus(String code, String codeKorName, String codeEngName) {
        this.code=code;
        this.codeKorName=codeKorName;
        this.codeEngName=codeEngName;
    }

    public static ApplicationStatus fromCode(String dbData){
        return Arrays.stream(ApplicationStatus.values())
                .filter(v->v.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new CommonException(ErrorCode.INVALID_INPUT_VALUE)) // TODO: 추후 변경
                ;
    }
}
