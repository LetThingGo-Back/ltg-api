package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.dto.UserCreateRequest;
import com.letthinggo.ltgapi.data.entity.common.BaseDateTime;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="USERS")
public class Users extends BaseDateTime{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private Long id;
    @Column(name="NICKNAME")
    private String nickname;
    @Column(name="EMAIL")
    private String email;
//    @CreatedDate
    @Column(name="JOIN_DATE", updatable = false)
    private String joinDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFILE_IMAGE_ID")
    private Attach attach;

    @OneToMany(mappedBy = "user")
    private List<SocialLogin> socialLogins = new ArrayList<>();

    @PrePersist
    public void onPrePersist() {
        String customLocalDateTimeFormat = LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.joinDate = customLocalDateTimeFormat;
    }

    @Builder
    public Users(String nickname, String email, Attach attach) {
        this.nickname = nickname;
        this.email = email;
        this.attach = attach;
    }

    public static Users createUsers(UserCreateRequest userDto){
        return Users.builder().nickname(userDto.getNickname())
                .email(userDto.getEmail())
                .build();
    }
}
