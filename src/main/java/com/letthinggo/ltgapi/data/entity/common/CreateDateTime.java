package com.letthinggo.ltgapi.data.entity.common;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDateTime;

@Embeddable
@Getter
public class CreateDateTime {

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
