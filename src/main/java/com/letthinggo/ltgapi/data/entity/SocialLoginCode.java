package com.letthinggo.ltgapi.data.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SocialLoginCode {
    KAKAO("K" , "kakao"),
    NAVER("N", "naver"),
    GOOGLE("G", "google");
    private String code;
    private String socialName;

    SocialLoginCode(String code, String socialName) {
        this.code = code;
        this.socialName = socialName;
    }

    public static SocialLoginCode fromCode(String dbData){
        return Arrays.stream(SocialLoginCode.values())
                .filter(v->v.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException(String.format("소셜로그인코드에 %가 존재하지 않습니다.",dbData)));
    }
}
