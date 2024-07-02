package com.letthinggo.ltgapi.domain.item.data.entity.item;

import com.letthinggo.ltgapi.global.exception.CommonException;
import com.letthinggo.ltgapi.global.exception.ErrorCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ItemStatus {

    UNOPEND ("11","포장도뜯지않은새것","UNOPEND"),
    UNUSED("12","거의사용안해서새것같음","UNUSED"),
    USED("13","사용한지좀되었지만관리가잘됨","USED"),
    NOISSUE("14","중고처럼보이나사용하는데문제없음","NOISSUE"),
    NOTTRASH("15","버리기애매함","NOTTRASH"),

    FRESHLYMADE("21","오늘만듦","FRESHLYMADE"),
    PERISHABLE("22","오늘안에소비해야함","PERISHABLE"),
    LONGLASTING("23","소비기한2일이상","LONGLASTING"),
    EXTENDED("24","소비기한1주일이상남음","EXTENDED"),
    SUSTAINED("25","소비기한1달이상남음","SUSTAINED")
    ;

    private String code;
    private String codeKorName;
    private String codeEngName;

    ItemStatus(String code, String codeKorName, String codeEngName) {
        this.code=code;
        this.codeKorName=codeKorName;
        this.codeEngName=codeEngName;
    }

    public static ItemStatus fromCode(String dbData){
        return Arrays.stream(ItemStatus.values())
                .filter(v->v.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new CommonException(ErrorCode.INVALID_INPUT_VALUE)) // TODO: 추후 변경
                ;
    }

}
