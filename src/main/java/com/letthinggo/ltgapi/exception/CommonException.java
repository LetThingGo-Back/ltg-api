package com.letthinggo.ltgapi.exception;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException{
    private ErrorCode errorCode;


    public CommonException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CommonException(ErrorCode errorCode, Object... message){
        super(String.format(errorCode.getMessage(), message));
        this.errorCode = errorCode;
    }
}
