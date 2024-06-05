package com.letthinggo.ltgapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE( 400, "Invalid Input Value"),
    ENTITY_NOT_FOUND(404," Entity Not Found"),
    METHOD_NOT_ALLOWED(405,"Method Not Allowed"),
    INTERNAL_SERVER_ERROR(500,"Server Error"),

    // User 10100 ~ 10999
    /**
     *  로그인 10100 ~ 10199
     */
    USER_UNAUTORIZED( 10101,"로그인이 필요합니다."),
    USER_ACCESS_DENIED( 10102,"접근 권한이 없습니다."),
    REFRESH_TOKEN_NOT_FOUND(10103, "Refresh Token Not Found"),
    REFRESH_TOKEN_EXPIRED(10104, "Refresh Token Expired"),
    INVAILD_REFRESH_TOKEN(10105, "Invalid Refresh Token"),
    REFRESH_TOKEN_NULL(10106, "Refresh Token Is Null"),
    ACCESS_TOKEN_EXPIRED( 10107,"Access Token Expired"),
    INVAILD_ACCESS_TOKEN(10108, "Invalid Access Token"),

    INVAILD_SOCIAL_CODE(10108, "소셜로그인코드에 %s가 존재하지 않습니다."),

    INVAILD_SOCIAL_NAME(10108, "%s는 잘못된 provider입니다."),
    /**
     * 사용자 정보 조회 10200 ~ 10299
     */
    USER_NOT_FOUND(10201, "사용자가 존재하지 않습니다."),

     /*
        나눔물품 20100 ~ 20999
     * */
    INVAILD_AVAILABILTY_CODE(20100,"나눔가능시간등록자구분코드에 %s가 존재하지 않습니다.")


    ;
    private final int code;
    private final String message;


}
