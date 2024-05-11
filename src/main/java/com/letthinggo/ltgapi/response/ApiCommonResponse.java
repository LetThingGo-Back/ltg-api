package com.letthinggo.ltgapi.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.letthinggo.ltgapi.response.ApiStatus.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiCommonResponse<T> {

//    private static final String SUCCESS_STATUS = "success";
//    private static final String FAIL_STATUS = "fail";
//    private static final String ERROR_STATUS = "error";

    private String status;
    private T data;
    private String message;

    public static <T> ApiCommonResponse<T> createSuccess(T data) {
        return new ApiCommonResponse<>(SUCCESS.getStatus(), data, null);
    }

    public static ApiCommonResponse<?> createSuccessWithNoContent() {
        return new ApiCommonResponse<>(SUCCESS.getStatus(), null, null);
    }

    // Hibernate Validator에 의해 유효하지 않은 데이터로 인해 API 호출이 거부될때 반환
    public static ApiCommonResponse<?> createFail(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();

        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError error : allErrors) {
            if (error instanceof FieldError) {
                errors.put(((FieldError) error).getField(), error.getDefaultMessage());
            } else {
                errors.put( error.getObjectName(), error.getDefaultMessage());
            }
        }
        return new ApiCommonResponse<>(FAIL.getStatus(), errors, null);
    }

    // 예외 발생으로 API 호출 실패시 반환
    public static ApiCommonResponse<?> createError(String message) {
        return new ApiCommonResponse<>(ERROR.getStatus(), null, message);
    }

    private ApiCommonResponse(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}