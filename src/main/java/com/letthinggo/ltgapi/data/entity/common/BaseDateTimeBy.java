package com.letthinggo.ltgapi.data.entity.common;

import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseDateTimeBy extends BaseDateTime{
    @Column(name="CREATEOR_ID")
    private Long creatorId;
    @Column(name="UPDATER_ID")
    private Long updaterId;
}
