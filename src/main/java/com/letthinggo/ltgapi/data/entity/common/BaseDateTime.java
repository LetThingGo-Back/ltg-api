package com.letthinggo.ltgapi.data.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
//    @PrePersist
//    public void onPrePersist() {
//        this.createdDate =  LocalDateTime.now(ZoneId.of("Asia/Seoul"));
//        this.updatedDate =  LocalDateTime.now(ZoneId.of("Asia/Seoul"));
//    }
}
