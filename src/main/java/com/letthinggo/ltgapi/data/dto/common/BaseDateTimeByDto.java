package com.letthinggo.ltgapi.data.dto.common;

import lombok.Data;
import lombok.Getter;

@Data
public class BaseDateTimeByDto extends BaseDateTimeDto {
    private Long creatorId;

    private Long updaterId;
}
