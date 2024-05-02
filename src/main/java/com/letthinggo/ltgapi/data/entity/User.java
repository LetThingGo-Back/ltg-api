package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.entity.common.CreateDateTimeBy;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue
    @Column(name="USER_ID")
    private Long id;

    private String nickname;

    private String email;

    private Long profileImageId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTACH_ID")
    private Attach attach;

    @Embedded
    private CreateDateTimeBy createDateTimeBy;
}
