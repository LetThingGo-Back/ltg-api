package com.letthinggo.ltgapi.domain.category.data.entity;

import com.letthinggo.ltgapi.global.audit.BaseDateTimeBy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "CATEGORY")
public class Category extends BaseDateTimeBy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID", nullable = false)
    private Long id;

    @Column(name = "CATEGORY_NAME", nullable = false, length = 150)
    private String categoryName;

    @Column(name = "SEQ", nullable = false)
    private Integer seq;

    @Column(name = "USE_YN", nullable = false)
    private Character useYn;

}