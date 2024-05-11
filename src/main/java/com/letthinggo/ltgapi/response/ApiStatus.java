package com.letthinggo.ltgapi.response;

import lombok.Getter;

@Getter
public enum ApiStatus {
    SUCCESS("success"),
    FAIL("fail"),
    ERROR("error");
    private String status;
    ApiStatus(String status) {
        this.status = status;
    }
}
