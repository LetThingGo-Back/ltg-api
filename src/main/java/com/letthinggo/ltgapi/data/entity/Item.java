package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.entity.common.BaseDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "ITEM")
public class Item extends BaseDateTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID", nullable = false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private Users user;

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    @Column(name = "ITEM_STATUS", nullable = false)
    private Character itemStatus;

    @Column(name = "ITEM_IMAGE_ID", nullable = false)
    private Integer itemImageId;

    @Column(name = "THUM_IMAGE_ID", nullable = false)
    private Integer thumImageId;

    @Column(name = "ITEM_NAME", nullable = false, length = 150)
    private String itemName;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Column(name = "CONFIRMED_DATE", length = 8)
    private String confirmedDate;

    @Column(name = "CONFIRMED_TIME", length = 4)
    private String confirmedTime;

    @Column(name = "PROGRESS_CODE", nullable = false)
    private ProgressCode progressCode;

    @Column(name = "DEL_YN")
    private Character delYn;

}