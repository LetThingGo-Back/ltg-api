package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.entity.common.BaseDateTimeBy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    private List<Item> items = new ArrayList<>();
}