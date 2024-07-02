package com.letthinggo.ltgapi.domain.item.data.entity.availability;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import lombok.extern.slf4j.Slf4j;

@Convert
@Slf4j
public class AvailabiltyCodeConverter implements AttributeConverter<AvailabiltyCode, String> {

    @Override
    public String convertToDatabaseColumn(AvailabiltyCode availabiltyCode) {
        if(availabiltyCode == null) return null;
        return availabiltyCode.getCode();
    }

    public AvailabiltyCode convertToEntityAttribute(String dbData) {
        if(dbData == null) {
            return null;
        }
        try{
            return AvailabiltyCode.fromCode(dbData);
        }catch (IllegalStateException e) {
            log.error("failure to convert cause unexpected code[{}]", dbData, e);
            throw e;
        }
    }
}
