package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.entity.common.BaseDateTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="USER_TERMS")
public class UserTerms {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_TERMS_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private Users user;
    @ManyToOne
    @JoinColumn(name="TERMS_ID")
    private Terms terms;
    @Column(name="AGREE_YN")
    private String agreeYn;
    @Column(name="CREATED_DATE")
    private LocalDateTime createdDate;
}
