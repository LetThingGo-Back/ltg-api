package com.letthinggo.ltgapi.data.dto.common;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public abstract class BaseDateTimeDto {
    private Date createdDate;
    private Date updatedDate;
}
