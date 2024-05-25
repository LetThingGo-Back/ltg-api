package com.letthinggo.ltgapi.exception;

import com.letthinggo.ltgapi.response.ApiCommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice // 모든 컨드롤러가 실행될 때 반드시 이 exception handler가 실행 된다.
public class CustomizedResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiCommonResponse<?>> handleAllExceptions(Exception ex){
        return ResponseEntity.status(HttpStatus.OK).body(ApiCommonResponse.createError(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiCommonResponse<?>> handleValidationExceptions(MethodArgumentNotValidException ex, BindingResult bindingResult) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiCommonResponse.createFail(bindingResult));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ApiCommonResponse<?>> handleUserNotFoundExceptions(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.OK).body(ApiCommonResponse.createErrorWithCode(ex.getErrorCode()));
    }
}
