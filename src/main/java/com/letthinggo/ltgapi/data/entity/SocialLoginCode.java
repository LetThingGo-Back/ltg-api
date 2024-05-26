package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.exception.CommonException;
import com.letthinggo.ltgapi.exception.ErrorCode;
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
                .orElseThrow(()-> new CommonException(ErrorCode.INVAILD_SOCIAL_CODE, dbData));
    }

    public static boolean checkSocialName(String socialName){
        Arrays.stream(SocialLoginCode.values())
                .filter(v->v.getSocialName().equals(socialName))
                .findAny()
                .orElseThrow(()-> new CommonException(ErrorCode.INVAILD_SOCIAL_NAME, socialName));
        return true;
    }
}
