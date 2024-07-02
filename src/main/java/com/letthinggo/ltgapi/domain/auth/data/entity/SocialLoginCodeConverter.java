package com.letthinggo.ltgapi.domain.auth.data.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import lombok.extern.slf4j.Slf4j;


@Convert
@Slf4j
public class SocialLoginCodeConverter implements AttributeConverter<SocialLoginCode, String> {

    @Override
    public String convertToDatabaseColumn(SocialLoginCode socialLoginCode) {
        if(socialLoginCode == null) return null;
        return socialLoginCode.getCode();
    }

    @Override
    public SocialLoginCode convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }
        try{
            return SocialLoginCode.fromCode(dbData);
        } catch (IllegalStateException e){
            log.error("failure to convert cause unexpected code[{}]", dbData, e);
            throw e;
        }
    }
}
