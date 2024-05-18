package com.letthinggo.ltgapi.data.entity;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @CreatedDate
    @Column(name="CREATED_DATE", updatable = false)
    private LocalDateTime createdDate;
    @Builder
    public UserTerms(Users user, Terms terms, String agreeYn) {
        this.user = user;
        this.terms = terms;
        this.agreeYn = agreeYn;
    }
    public static List<UserTerms> createUserTerms(Map<String, String> userAgreements, Users user, List<Terms> terms){
        List<UserTerms> userTerms = new ArrayList<>();
        for(Terms t : terms){
            String agreeYn = "N";
            if(!StringUtils.isBlank(userAgreements.get(t.getTermsCode()))){
                agreeYn=userAgreements.get(t.getTermsCode());
            }
            userTerms.add(UserTerms.builder().user(user)
                                             .terms(t)
                                             .agreeYn(agreeYn)
                                    .build());
        }

        return userTerms;
    }
}
