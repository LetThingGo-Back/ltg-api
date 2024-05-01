package com.letthinggo.ltgapi.data.entity.common;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;

@Embeddable
@Getter
public class CreateDateTimeBy {

    @Embedded
    private CreateDateTime dateTime;
    private Long creatorId;
    private Long updaterId;
}
