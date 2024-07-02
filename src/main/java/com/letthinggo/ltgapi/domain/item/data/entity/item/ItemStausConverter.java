package com.letthinggo.ltgapi.domain.item.data.entity.item;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import lombok.extern.slf4j.Slf4j;


@Convert
@Slf4j
public class ItemStausConverter implements AttributeConverter<ItemStatus, String> {

    @Override
    public String convertToDatabaseColumn(ItemStatus requestStatus) {
        if(requestStatus == null) return null;
        return requestStatus.getCode();
    }

    @Override
    public ItemStatus convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }
        try{
            return ItemStatus.fromCode(dbData);
        } catch (IllegalStateException e){
            log.error("failure to convert cause unexpected code[{}]", dbData, e);
            throw e;
        }
    }
}
