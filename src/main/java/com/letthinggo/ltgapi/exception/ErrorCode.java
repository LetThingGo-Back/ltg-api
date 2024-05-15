package com.letthinggo.ltgapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE( "400", "Invalid Input Value"),
    ENTITY_NOT_FOUND("404"," Entity Not Found"),
    METHOD_NOT_ALLOWED("405","Method Not Allowed"),
    INTERNAL_SERVER_ERROR("500","Server Error"),

    // User 10100 ~ 10999
    /**
     *  로그인 10100 ~ 10199
     */
    USER_UNAUTORIZED( "10101","로그인이 필요합니다."),
    USER_ACCESS_DENIED( "10102","권한이 없습니다."),
    /**
     * 사용자 정보 조회 10200 ~ 10299
     */
    USER_NOT_FOUND("10201", "사용자가 존재하지 않습니다."),

    // Item 20100 ~ 20999



    ;
    private final String code;
    private final String message;


}
