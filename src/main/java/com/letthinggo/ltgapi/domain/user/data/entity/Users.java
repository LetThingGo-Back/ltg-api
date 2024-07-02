package com.letthinggo.ltgapi.domain.user.data.entity;

import com.letthinggo.ltgapi.domain.attach.data.entity.Attach;
import com.letthinggo.ltgapi.domain.auth.data.entity.SocialLogin;
import com.letthinggo.ltgapi.domain.user.data.dto.UserDto;
import com.letthinggo.ltgapi.global.audit.BaseDateTime;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public static Users createUsers(UserDto userDto){
        return Users.builder().nickname(userDto.getNickname())
                .email(userDto.getEmail())
                .build();
    }
}
