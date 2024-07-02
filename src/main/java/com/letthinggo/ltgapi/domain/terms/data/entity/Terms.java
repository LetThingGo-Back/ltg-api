package com.letthinggo.ltgapi.domain.terms.data.entity;

import com.letthinggo.ltgapi.global.audit.BaseDateTimeBy;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="TERMS")
public class Terms extends BaseDateTimeBy{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TERMS_ID")
    private Long id;
    @Column(name="TERMS_CODE")
    private String termsCode;
    @Column(name="TERMS_NAME")
    private String termsName;
    @Column(name="TERMS_CONTENT")
    private String termsContent;
    @Column(name="REQ_YN")
    private String reqYn;
    @Column(name="USE_YN")
    private String useYn;
    @Column(name="TERMS_START_DATE")
    private LocalDateTime termsStartDate;
    @Column(name="TERMS_END_DATE")
    private LocalDateTime termsEndDate;
}
