package com.letthinggo.ltgapi.data.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTerms {
    @Id @GeneratedValue
    @Column(name="USER_TERMS_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name="TERMS_ID")
    private Terms terms;
    private String agreeYn;
    private LocalDateTime createdTime;
}
