package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.entity.common.BaseDateTime;
import com.letthinggo.ltgapi.data.entity.common.BaseDateTimeBy;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="ATTACH")
public class Attach extends BaseDateTimeBy {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ATTACH_ID")
    private Long id;
    @Column(name="ATTACH_SEQ")
    private String attachSeq;
    @Column(name="ATTACH_NAME")
    private String attachName;
    @Column(name="ATTACH_SIZE")
    private int attachSize;
    @Column(name="ATTACH_URL")
    private String attachUrl;
    @Column(name="ATTACH_EXTS")
    private String attachExts;
}
