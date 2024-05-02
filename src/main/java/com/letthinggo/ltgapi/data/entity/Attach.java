package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.entity.common.CreateDateTimeBy;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Attach {
    @Id @GeneratedValue
    @Column(name="ATTACH_ID")
    private Long id;
    private String attachSeq;
    private String attachName;
    private int attachSize;
    private String attachUrl;
    private String attachExts;
    @Embedded
    private CreateDateTimeBy createDateTimeBy;
}
