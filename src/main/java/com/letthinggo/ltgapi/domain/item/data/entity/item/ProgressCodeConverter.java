package com.letthinggo.ltgapi.domain.item.data.entity.item;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import lombok.extern.slf4j.Slf4j;


@Convert
@Slf4j
public class ProgressCodeConverter implements AttributeConverter<ProgressCode, String> {

    @Override
    public String convertToDatabaseColumn(ProgressCode requestStatus) {
        if(requestStatus == null) return null;
        return requestStatus.getCode();
    }

    @Override
    public ProgressCode convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }
        try{
            return ProgressCode.fromCode(dbData);
        } catch (IllegalStateException e){
            log.error("failure to convert cause unexpected code[{}]", dbData, e);
            throw e;
        }
    }
}
