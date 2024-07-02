package com.letthinggo.ltgapi.global.util;

import jakarta.servlet.http.Cookie;

public class CookieUtil {

    public static Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(30 * 24 * 60 * 60); // 30일
//        cookie.setMaxAge(60); // 30일
        /*cookie.setSecure();*/         // https 설정
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    public static String getRefreshToken(Cookie[] cookies) {
        String refreshToken = "";
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("refreshToken")) {
                refreshToken = cookie.getValue();
            }
        }
        return refreshToken;
    }
}
