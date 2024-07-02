package com.letthinggo.ltgapi.global.exception;

import lombok.Getter;
@Getter
public class UserNotFoundException extends RuntimeException{
    private ErrorCode errorCode;

    public UserNotFoundException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
