package com.letthinggo.ltgapi.global.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseDateTimeBy extends BaseDateTime{
    @CreatedBy
    @Column(name="CREATOR_ID", updatable = false)
    private Long creatorId;

    @LastModifiedBy
    @Column(name="UPDATER_ID")
    private Long updaterId;
}
