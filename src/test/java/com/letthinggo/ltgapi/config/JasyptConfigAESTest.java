package com.letthinggo.ltgapi.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.junit.jupiter.api.Test;

class JasyptConfigAESTest {

    @Test
    public void stringEncryptor() {
        String url = "your_db_url";
        String username = "your_db_username";
        String password = "your_db_password";

        System.out.println("url="+jasyptEncoding(url));
        System.out.println("username="+jasyptEncoding(username));
        System.out.println("password="+jasyptEncoding(password));
    }

    public String jasyptEncoding(String value) {

        String key = "your_secret_key";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        pbeEnc.setPassword(key);
        pbeEnc.setIvGenerator(new RandomIvGenerator());
        return pbeEnc.encrypt(value);
    }

}