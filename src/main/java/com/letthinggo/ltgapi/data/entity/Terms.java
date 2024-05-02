package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.entity.common.CreateDateTimeBy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms {
    @Id @GeneratedValue
    @Column(name="TERMS_ID")
    private Long id;
    private String termsName;
    private String termsContent;
    private String reqYn;
    private String useYn;
    private LocalDateTime termsStartDate;
    private LocalDateTime termsEndDate;
    private CreateDateTimeBy createDateTimeBy;
}
