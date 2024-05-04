package com.letthinggo.ltgapi.data.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SocialLoginCode {
    KAKAO("K"),
    NAVER("N"),
    GOOGLE("G");
    private String code;
    SocialLoginCode(String code) {
        this.code = code;
    }

    public static SocialLoginCode fromCode(String dbData){
        return Arrays.stream(SocialLoginCode.values())
                .filter(v->v.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException(String.format("소셜로그인코드에 %가 존재하지 않습니다.",dbData)));
    }
}
