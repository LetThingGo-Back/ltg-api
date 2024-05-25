package com.letthinggo.ltgapi.util;

import java.security.SecureRandom;
import java.util.Random;

public class NicknameGenerator {
    private static final String[] ADJECTIVES = {
            "귀여운", "멋진", "빠른", "슬기로운", "행복한", "용감한"
            ,"상냥한","지혜로운","다정한","강력한","활기찬","친절한"
            ,"씩씩한","재치있는","유쾌한","순수한","온화한"
            ,"매력적인","기분좋은","신비로운","호기심많은"
            ,"명랑한","화려한","든든한","선한","진지한","다재다능한","정직한","신중한"
            ,"예의바른","헌신적인","차분한","털털한","활발한" ,"자유로운"
            ,"모험적인","재능있는","열정적인","성실한","따뜻한","깔끔한"
    };

    private static final String[] NOUNS = {
            "고양이", "사자", "호랑이", "토끼", "다람쥐"
            ,"사과", "바나나", "귤", "고구마", "키워", "수박"
            ,"장미","해바라기","튤립","구름","시계","바람","소나무","은행나무"
            ,"꿀벌","코스모스","벚꽃","장미", "라일락","바다","별빛","모래"
            ,"소나무","대나무","체리","씨앗","구슬"
            ,"리본","깃털","지갑","열쇠","책상","의자","창문","그림"
            ,"사진","상자","별똥별","눈사람","우산","토마토","당근"
    };

    private static final Random RANDOM = new SecureRandom();

    public static String generateRandomNickname() {
        String adjective = ADJECTIVES[RANDOM.nextInt(ADJECTIVES.length)];
        String noun = NOUNS[RANDOM.nextInt(NOUNS.length)];
        int number = RANDOM.nextInt(100); // 0부터 99까지의 숫자 추가
        return adjective + noun + number;
    }
}
