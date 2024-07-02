package com.letthinggo.ltgapi.global.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseDateTime {
    @CreatedDate
    @Column(name="CREATED_DATE", updatable = false)
    private Date createdDate;

    @LastModifiedDate
    @Column(name="UPDATED_DATE" )
    private Date updatedDate;
}
