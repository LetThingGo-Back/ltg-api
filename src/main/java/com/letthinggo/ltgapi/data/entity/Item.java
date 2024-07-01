package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.dto.ItemDto;
import com.letthinggo.ltgapi.data.entity.common.BaseDateTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name = "ITEM")
public class Item extends BaseDateTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CONTRIBUTOR_ID")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    @Convert(converter = ItemStausConverter.class)
    @Column(name = "ITEM_STATUS")
    private ItemStatus itemStatus;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CONFIRMED_DATE")
    private String confirmedDate;

    @Column(name = "CONFIRMED_TIME")
    private String confirmedTime;

    @Convert(converter = ProgressCodeConverter.class)
    @Column(name = "PROGRESS_CODE")
    private ProgressCode progressCode;

    @Column(name = "DEL_YN")
    private Character delYn;

    @Builder
    public Item (Users user, Category category,ItemStatus itemStatus,String itemName,String description,String confirmedDate,String confirmedTime,ProgressCode progressCode,Character delYn) {
        this.user = user;
        this.category = category;
        this.itemStatus = itemStatus;
        this.itemName = itemName;
        this.description = description;
        this.confirmedDate = confirmedDate;
        this.confirmedTime = confirmedTime;
        this.progressCode = progressCode;
        this.delYn = delYn;
    }

    //TODO 추후 수정예정
    public static Item createItem(ItemDto.CreateRequest createRequest, Users users, Category category) {
        return Item.builder()
                .user(users)
                .itemStatus(ItemStatus.fromCode(createRequest.getItemStatus()))
                .itemName(createRequest.getItemName())
                .category(category)
                .description(createRequest.getItemDescription())
                .progressCode(ProgressCode.WAITING)
                .build();
    }

//    public static Item createItem(Users user, Category category,ItemStatus itemStatus,String itemName,String description,String confirmedDate,String confirmedTime,ProgressCode progressCode) {
//        return Item.builder()
//                .user(user)
//                .itemStatus(itemStatus)
//                .itemName(itemName)
//                .category(category)
//                .description(description)
//                .confirmedDate(confirmedDate)
//                .confirmedTime(confirmedTime)
//                .progressCode(progressCode)
//                .build();
//    }


}